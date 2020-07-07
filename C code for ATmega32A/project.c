#define F_CPU 8000000UL
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
#include "LCD16x2_4bit.c"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "uart.h"

#define DHT11_PIN PA6
#define btnpress !(PIND & (1<<PIND2))

#define SREG   _SFR_IO8(0x3F)
#define BAUD_PRESCALE (((F_CPU / (BAUDRATE * 8UL))) - 1) /* Define prescale value */ ////Asynchronous Normal Mode/////

#define SSID "HUAWEI_Y7_Pro_2018"
#define PASSWORD "123456789"

#define API_KEY "P0YBGXDU86GPTES5"

char str[50];
int len = 0;
char receive_data;
unsigned char tempepature,humidity;

uint8_t c=0,I_RH,D_RH,I_Temp,D_Temp,CheckSum;
char data[5];

void USART_Init(unsigned long);       /* USART initialize function */
//char USART_RxChar();            /* Data receiving function */
void USART_TxChar(char);          /* Data transmitting function */
void USART_SendString(char*);       /* Send string of USART data function */
int uart_getchar(FILE*);
uint8_t UART_RxString(char *ptr_string);
void UART_Printf(const char *argList, ...);
void WifiSendData(unsigned char *tem, unsigned char *hum,char *doo);
//void ConnectWifi();


void Request(){				/* Microcontroller send start pulse/request */

	DDRA |= (1<<DHT11_PIN);
	PORTA &= ~(1<<DHT11_PIN);	/* set to low pin */
	_delay_ms(20);			/* wait for 20ms */
	PORTA |= (1<<DHT11_PIN);	/* set to high pin */
}

void Response(){				/* receive response from DHT11 */

	DDRA &= ~(1<<DHT11_PIN);
	while(PINA & (1<<DHT11_PIN));
	while((PINA & (1<<DHT11_PIN))==0);
	while(PINA & (1<<DHT11_PIN));
}

uint8_t Receive_data(){			/* receive data */	
	
	for (int q=0; q<8; q++){
		while((PINA & (1<<DHT11_PIN)) == 0);  /* check received bit 0 or 1 */
		_delay_us(30);
		if(PINA & (1<<DHT11_PIN))/* if high pulse is greater than 30ms */
		c = (c<<1)|(0x01);	/* then its logic HIGH */
		else			/* otherwise its logic LOW */
		c = (c<<1);
		while(PINA & (1<<DHT11_PIN));
	}
	return c;
}

void Read_DHT11(){
 	
 	Request();				/* send start pulse */
	Response();				/* receive response */
	I_RH=Receive_data();	/* store first eight bit in I_RH */
	D_RH=Receive_data();	/* store next eight bit in D_RH */
	I_Temp=Receive_data();	/* store next eight bit in I_Temp */
	D_Temp=Receive_data();	/* store next eight bit in D_Temp */
	CheckSum=Receive_data();/* store next eight bit in CheckSum */

	if ((I_RH + D_RH + I_Temp + D_Temp) != CheckSum){

		lcd_gotoxy(0,0);
		lcd_print("Error");
		
	}else{

		itoa(I_RH,data,10);
		lcd_gotoxy(11,0);
		lcd_print(data);
		lcd_print(".");
			
		itoa(D_RH,data,10);
		lcd_print(data);
		lcd_print("%");

		itoa(I_Temp,data,10);
		lcd_gotoxy(6,1);
		lcd_print(data);
		lcd_print(".");
			
		itoa(D_Temp,data,10);
		lcd_print(data);
		lcddata(0xDF);
		lcd_print("C ");

		//tempepature = (char)I_Temp;
	
	}
 
 }

int DoorCheck(){
 	if (btnpress){
		PORTC |=(1<<6);
		PORTC &=~(1<<5);
		lcd_clear();
		lcd_gotoxy(4,0);
		lcd_print("Warning");
		lcd_gotoxy(2,1);
		lcd_print("Door is Open");
		_delay_ms(1000);
	}else {
		PORTC |=(1<<5);
		PORTC &=~(1<<6);
		lcd_clear();
		lcd_gotoxy(1,1);
		lcd_print("Door is Closed");
		_delay_ms(1000);		
	}
	return 0;
}

ISR(INT0_vect){
	DoorCheck();

}	

int main(void){

	//USART_Init(19200);
	lcdinit();					/* initialize LCD */
	lcd_clear();				/* clear LCD */
	lcd_gotoxy(0,0);				
	lcd_print("ROOM TEMPERATURE");
	lcd_gotoxy(3,1);				
	lcd_print("CONTROLLER");
	_delay_ms(1000);
	lcd_clear();
	lcd_gotoxy(0,0);			/* enter column and row position */
	lcd_print("Humidity = ");
	lcd_gotoxy(0,1);
	lcd_print("Temp = ");
    //ConnectWifi();

	DDRC = 0xff;
	DDRD &= ~(1<<INT0); //Configure INT0(PD2) as input
	GICR = 1<<INT0; //Enable external interrupt 0 and interrupt 1
	MCUCR = 0<<ISC01 | 1<<ISC00; //The rising edge of INT0 generates an interrupt request
	sei(); //Enable global interrupts

	UCSRA = 0X00;                    /* Clears TXC & RXC Flag Bit                        */
 	UCSRB = 0X18;                    /* Transmission & Reception Enable (TXEN=1, RXEN=1) */                                                        
 	UCSRC = 0X86;                    /* URSEL=1,UMSEL=0,UCSZ1=1,UCSZ0=0                  */

	_delay_ms(100);
	DoorCheck();
	USART_Init(19200);
	ConnectWifi();

	while (1){
		Read_DHT11();
		
		lcd_clear();				/* clear LCD */
		lcd_gotoxy(0,0);			/* enter column and row position */
		lcd_print("Humidity = ");
		lcd_gotoxy(0,1);
		lcd_print("Temp = ");

    	ConnectWifi();
		WifiSendData("35","90","1");
      	_delay_ms(5000);


	}
	return 0;
}


void ConnectWifi(){
  USART_SendString("AT\r\n");

    while(1){
      len = UART_RxString(str);
      char chk2[4] = "OK";
      if(strncmp(chk2,str,2)==0){
        break;
      }
    }

    USART_SendString("AT+CWJAP=\"");
    USART_SendString(SSID);
    USART_SendString("\",\"");
    USART_SendString(PASSWORD);
    USART_SendString("\"\r\n");

    while(1){
      len = UART_RxString(str);
      char chk2[4] = "OK";
      if(strncmp(chk2,str,2)==0){
        break;
      }
    }
    //USART_SendString("AT\r\n");
    //_delay_ms(2000);
  
    USART_SendString("AT+CWMODE=3\r\n");  
    while(1){
      len = UART_RxString(str);
      char chk2[4] = "OK";
      if(strncmp(chk2,str,2)==0){
        break;
      }
    }
    
    USART_SendString("AT+CIPMUX=0\r\n");   
    while(1){
      len = UART_RxString(str);
      char chk2[4] = "OK";
      if(strncmp(chk2,str,2)==0){
        break;
      }
    }
    
}

void WifiSendData(unsigned char *tem, unsigned char *hum,char *doo){

    USART_SendString("AT+CIPSTART=\"TCP\",\"api.thingspeak.com\",80\r\n");
    while(1){
      len = UART_RxString(str);
      char chk2[4] = "OK";
      if(strncmp(chk2,str,2)==0){
        break;
      }
    }

    USART_SendString("AT+CIPSEND=95\r\n");
    while(1){
      len = UART_RxString(str);
      char chk2[3] = "OK";
      if(strncmp(chk2,str,2)==0){
        break;
      }
    }
    
    for (int i = 0; i < 3; i++)
    {
        USART_SendString("GET https://api.thingspeak.com/update?api_key=");
        USART_SendString(API_KEY);
        USART_SendString("&field1=");
        USART_SendString(tem);
        USART_SendString("&field2=");
        USART_SendString(hum);
        USART_SendString("&field3=");
        USART_SendString(doo);
        USART_SendString("\r\n");
        
        len = UART_RxString(str);
        char chk3[8] = "SEND OK";
        if(strncmp(chk3,str,7)==0){
        break;
        }
          _delay_ms(2000);
    }
    
}

void USART_Init(unsigned long BAUDRATE)       /* USART initialize function */
{
    UCSRB |= (1 << RXEN) | (1 << TXEN) | (1 << RXCIE);        /* Enable USART transmitter and receiver */
    UCSRC |= (1 << URSEL)| (1 << UCSZ0) | (1 << UCSZ1); /* Write USCRC for 8 bit data and 1 stop bit */
    UBRRL = BAUD_PRESCALE;              /* Load UBRRL with lower 8 bit of prescale value */
    UBRRH = (BAUD_PRESCALE >> 8);         /* Load UBRRH with upper 8 bit of prescale value */
}

char USART_RxChar()                 /* Data receiving function */
{
  while (!(UCSRA & (1 << RXC)));          /* Wait until new data receive */
  return(UDR);                  /* Get and return received data */
}

void USART_TxChar(char data)            /* Data transmitting function */
{
  UDR = data;                   /* Write data to be transmitting in UDR */
  while (!(UCSRA & (1<<UDRE)));         /* Wait until data transmit and buffer get empty */
}

void USART_SendString(char *str)          /* Send string of USART data function */
{
  int i=0;
  while (str[i]!=0)
  {
    USART_TxChar(str[i]);           /* Send each char of string till the NULL */
    i++;
  }
}
