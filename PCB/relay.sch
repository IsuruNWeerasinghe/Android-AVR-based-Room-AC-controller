<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE eagle SYSTEM "eagle.dtd">
<eagle version="7.6.0">
<drawing>
<settings>
<setting alwaysvectorfont="no"/>
<setting verticaltext="up"/>
</settings>
<grid distance="0.1" unitdist="inch" unit="inch" style="lines" multiple="1" display="no" altdistance="0.01" altunitdist="inch" altunit="inch"/>
<layers>
<layer number="1" name="Top" color="4" fill="1" visible="no" active="no"/>
<layer number="2" name="Route2" color="1" fill="3" visible="no" active="no"/>
<layer number="3" name="Route3" color="4" fill="3" visible="no" active="no"/>
<layer number="4" name="Route4" color="1" fill="4" visible="no" active="no"/>
<layer number="5" name="Route5" color="4" fill="4" visible="no" active="no"/>
<layer number="6" name="Route6" color="1" fill="8" visible="no" active="no"/>
<layer number="7" name="Route7" color="4" fill="8" visible="no" active="no"/>
<layer number="8" name="Route8" color="1" fill="2" visible="no" active="no"/>
<layer number="9" name="Route9" color="4" fill="2" visible="no" active="no"/>
<layer number="10" name="Route10" color="1" fill="7" visible="no" active="no"/>
<layer number="11" name="Route11" color="4" fill="7" visible="no" active="no"/>
<layer number="12" name="Route12" color="1" fill="5" visible="no" active="no"/>
<layer number="13" name="Route13" color="4" fill="5" visible="no" active="no"/>
<layer number="14" name="Route14" color="1" fill="6" visible="no" active="no"/>
<layer number="15" name="Route15" color="4" fill="6" visible="no" active="no"/>
<layer number="16" name="Bottom" color="1" fill="1" visible="no" active="no"/>
<layer number="17" name="Pads" color="2" fill="1" visible="no" active="no"/>
<layer number="18" name="Vias" color="2" fill="1" visible="no" active="no"/>
<layer number="19" name="Unrouted" color="6" fill="1" visible="no" active="no"/>
<layer number="20" name="Dimension" color="15" fill="1" visible="no" active="no"/>
<layer number="21" name="tPlace" color="7" fill="1" visible="no" active="no"/>
<layer number="22" name="bPlace" color="7" fill="1" visible="no" active="no"/>
<layer number="23" name="tOrigins" color="15" fill="1" visible="no" active="no"/>
<layer number="24" name="bOrigins" color="15" fill="1" visible="no" active="no"/>
<layer number="25" name="tNames" color="7" fill="1" visible="no" active="no"/>
<layer number="26" name="bNames" color="7" fill="1" visible="no" active="no"/>
<layer number="27" name="tValues" color="7" fill="1" visible="no" active="no"/>
<layer number="28" name="bValues" color="7" fill="1" visible="no" active="no"/>
<layer number="29" name="tStop" color="7" fill="3" visible="no" active="no"/>
<layer number="30" name="bStop" color="7" fill="6" visible="no" active="no"/>
<layer number="31" name="tCream" color="7" fill="4" visible="no" active="no"/>
<layer number="32" name="bCream" color="7" fill="5" visible="no" active="no"/>
<layer number="33" name="tFinish" color="6" fill="3" visible="no" active="no"/>
<layer number="34" name="bFinish" color="6" fill="6" visible="no" active="no"/>
<layer number="35" name="tGlue" color="7" fill="4" visible="no" active="no"/>
<layer number="36" name="bGlue" color="7" fill="5" visible="no" active="no"/>
<layer number="37" name="tTest" color="7" fill="1" visible="no" active="no"/>
<layer number="38" name="bTest" color="7" fill="1" visible="no" active="no"/>
<layer number="39" name="tKeepout" color="4" fill="11" visible="no" active="no"/>
<layer number="40" name="bKeepout" color="1" fill="11" visible="no" active="no"/>
<layer number="41" name="tRestrict" color="4" fill="10" visible="no" active="no"/>
<layer number="42" name="bRestrict" color="1" fill="10" visible="no" active="no"/>
<layer number="43" name="vRestrict" color="2" fill="10" visible="no" active="no"/>
<layer number="44" name="Drills" color="7" fill="1" visible="no" active="no"/>
<layer number="45" name="Holes" color="7" fill="1" visible="no" active="no"/>
<layer number="46" name="Milling" color="3" fill="1" visible="no" active="no"/>
<layer number="47" name="Measures" color="7" fill="1" visible="no" active="no"/>
<layer number="48" name="Document" color="7" fill="1" visible="no" active="no"/>
<layer number="49" name="Reference" color="7" fill="1" visible="no" active="no"/>
<layer number="51" name="tDocu" color="6" fill="1" visible="no" active="no"/>
<layer number="52" name="bDocu" color="7" fill="1" visible="no" active="no"/>
<layer number="90" name="Modules" color="5" fill="1" visible="yes" active="yes"/>
<layer number="91" name="Nets" color="2" fill="1" visible="yes" active="yes"/>
<layer number="92" name="Busses" color="1" fill="1" visible="yes" active="yes"/>
<layer number="93" name="Pins" color="2" fill="1" visible="no" active="yes"/>
<layer number="94" name="Symbols" color="4" fill="1" visible="yes" active="yes"/>
<layer number="95" name="Names" color="7" fill="1" visible="yes" active="yes"/>
<layer number="96" name="Values" color="7" fill="1" visible="yes" active="yes"/>
<layer number="97" name="Info" color="7" fill="1" visible="yes" active="yes"/>
<layer number="98" name="Guide" color="6" fill="1" visible="yes" active="yes"/>
</layers>
<schematic xreflabel="%F%N/%S.%C%R" xrefpart="/%S.%C%R">
<libraries>
<library name="relay">
<description>&lt;b&gt;Relays&lt;/b&gt;&lt;p&gt;
&lt;ul&gt;
&lt;li&gt;Eichhoff
&lt;li&gt;Finder
&lt;li&gt;Fujitsu
&lt;li&gt;HAMLIN
&lt;li&gt;OMRON
&lt;li&gt;Matsushita
&lt;li&gt;NAiS
&lt;li&gt;Siemens
&lt;li&gt;Schrack
&lt;/ul&gt;
&lt;author&gt;Created by librarian@cadsoft.de&lt;/author&gt;</description>
<packages>
<package name="RELAY">
<pad name="P$1" x="0" y="6" drill="1.1" shape="octagon"/>
<pad name="P$2" x="0" y="-6" drill="1.1" shape="octagon"/>
<pad name="COM_CPEN" x="12.2" y="6" drill="1.1" shape="octagon"/>
<pad name="COM-CLOSE" x="12.2" y="-6" drill="1.1" shape="octagon"/>
<pad name="COM" x="-2" y="0" drill="1.1" shape="octagon"/>
<wire x1="-3.4" y1="7.8" x2="15.7" y2="7.8" width="0.127" layer="21"/>
<wire x1="15.7" y1="7.8" x2="15.7" y2="-7.8" width="0.127" layer="21"/>
<wire x1="-3.4" y1="7.8" x2="-3.4" y2="-7.8" width="0.127" layer="21"/>
<wire x1="-3.4" y1="-7.8" x2="15.7" y2="-7.8" width="0.127" layer="21"/>
<dimension x1="-3.4" y1="7.5" x2="15.7" y2="7.5" x3="6.15" y3="9" textsize="1.27" layer="47" dtype="horizontal"/>
<dimension x1="-3.4" y1="7.8" x2="-3.4" y2="-7.8" x3="-11" y3="0" textsize="1.27" layer="47" dtype="vertical"/>
<dimension x1="-8" y1="6" x2="-7.1" y2="-6" x3="-9" y3="0" textsize="1.27" layer="47" dtype="vertical"/>
<dimension x1="0" y1="-11" x2="-2" y2="0" x3="-1" y3="-11.2" textsize="1.27" layer="47" dtype="horizontal"/>
<dimension x1="0" y1="-11.1" x2="12.2" y2="-6" x3="6.1" y3="-15" textsize="1.27" layer="47" dtype="horizontal"/>
<dimension x1="-2" y1="-10" x2="-3.4" y2="-7.8" x3="-2.7" y3="-9.7" textsize="1.27" layer="47" dtype="horizontal"/>
<text x="2.54" y="1.27" size="1.27" layer="25">
</text>
<text x="2.54" y="-2.54" size="1.27" layer="27">
</text>
<wire x1="-1.27" y1="0" x2="0" y2="0" width="0.127" layer="21"/>
<wire x1="0" y1="0" x2="9.525" y2="0" width="0.127" layer="21"/>
<wire x1="12.065" y1="-5.08" x2="12.065" y2="-2.54" width="0.127" layer="21"/>
<wire x1="12.065" y1="-2.54" x2="9.525" y2="0" width="0.127" layer="21"/>
<wire x1="12.065" y1="5.08" x2="12.065" y2="2.54" width="0.127" layer="21"/>
<wire x1="0" y1="5.08" x2="0" y2="3.81" width="0.127" layer="21"/>
<wire x1="0" y1="-5.08" x2="0" y2="-3.81" width="0.127" layer="21"/>
<wire x1="0" y1="-3.81" x2="0" y2="-2.54" width="0.127" layer="21" curve="233.130102"/>
<wire x1="0" y1="-2.54" x2="0" y2="-1.27" width="0.127" layer="21" curve="233.130102"/>
<wire x1="0" y1="-1.27" x2="0" y2="0" width="0.127" layer="21" curve="233.130102"/>
<wire x1="0" y1="0" x2="0" y2="1.27" width="0.127" layer="21" curve="233.130102"/>
<wire x1="0" y1="1.27" x2="0" y2="2.54" width="0.127" layer="21" curve="233.130102"/>
<wire x1="0" y1="2.54" x2="0" y2="3.81" width="0.127" layer="21" curve="233.130102"/>
</package>
</packages>
<symbols>
<symbol name="RELAY">
<wire x1="-7.62" y1="2.54" x2="-5.08" y2="2.54" width="0.254" layer="94"/>
<wire x1="-5.08" y1="2.54" x2="-2.54" y2="2.54" width="0.254" layer="94"/>
<wire x1="-2.54" y1="2.54" x2="-2.54" y2="0" width="0.254" layer="94"/>
<wire x1="-2.54" y1="0" x2="-2.54" y2="-2.54" width="0.254" layer="94"/>
<wire x1="-2.54" y1="-2.54" x2="-5.08" y2="-2.54" width="0.254" layer="94"/>
<wire x1="-5.08" y1="-2.54" x2="-7.62" y2="-2.54" width="0.254" layer="94"/>
<wire x1="-7.62" y1="-2.54" x2="-7.62" y2="2.54" width="0.254" layer="94"/>
<wire x1="-5.08" y1="2.54" x2="-5.08" y2="7.62" width="0.254" layer="94"/>
<wire x1="-5.08" y1="-2.54" x2="-5.08" y2="-7.62" width="0.254" layer="94"/>
<wire x1="7.62" y1="0" x2="2.54" y2="-2.54" width="0.254" layer="94"/>
<wire x1="2.54" y1="2.54" x2="2.54" y2="7.62" width="0.254" layer="94"/>
<wire x1="2.54" y1="-2.54" x2="2.54" y2="-7.62" width="0.254" layer="94"/>
<wire x1="-10.16" y1="10.16" x2="12.7" y2="10.16" width="0.254" layer="94"/>
<wire x1="12.7" y1="10.16" x2="12.7" y2="-10.16" width="0.254" layer="94"/>
<wire x1="12.7" y1="-10.16" x2="-10.16" y2="-10.16" width="0.254" layer="94"/>
<wire x1="-10.16" y1="-10.16" x2="-10.16" y2="10.16" width="0.254" layer="94"/>
<circle x="2.54" y="-7.62" radius="1.27" width="0.254" layer="94"/>
<circle x="2.54" y="7.62" radius="1.27" width="0.254" layer="94"/>
<circle x="-5.08" y="7.62" radius="1.36783125" width="0.254" layer="94"/>
<circle x="-5.08" y="-7.62" radius="1.481059375" width="0.254" layer="94"/>
<circle x="7.62" y="0" radius="1.481059375" width="0.254" layer="94"/>
<pin name="IN" x="-5.08" y="7.62" visible="pad" length="point" function="dot"/>
<pin name="COM" x="7.62" y="0" visible="pad" length="point" function="dot"/>
<pin name="GND" x="-5.08" y="-7.62" visible="pad" length="point" function="dot"/>
<pin name="COM-CLOSE" x="2.54" y="-7.62" visible="pad" length="point" function="dot"/>
<pin name="COM-OPEN" x="2.54" y="7.62" visible="pad" length="point" function="dot"/>
<text x="-10.16" y="-12.7" size="1.27" layer="96">
</text>
<text x="-10.16" y="10.16" size="1.27" layer="95">
</text>
<wire x1="-2.54" y1="0" x2="3.556" y2="0" width="0.254" layer="94" style="shortdash"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="RELAY">
<gates>
<gate name="G$1" symbol="RELAY" x="-2.54" y="0"/>
</gates>
<devices>
<device name="" package="RELAY">
<connects>
<connect gate="G$1" pin="COM" pad="COM"/>
<connect gate="G$1" pin="COM-CLOSE" pad="COM-CLOSE"/>
<connect gate="G$1" pin="COM-OPEN" pad="COM_CPEN"/>
<connect gate="G$1" pin="GND" pad="P$2"/>
<connect gate="G$1" pin="IN" pad="P$1"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
<library name="con-molex">
<description>&lt;b&gt;Molex Connectors&lt;/b&gt;&lt;p&gt;
&lt;author&gt;Created by librarian@cadsoft.de&lt;/author&gt;</description>
<packages>
<package name="22-23-2031">
<description>.100" (2.54mm) Center Header - 3 Pin</description>
<wire x1="-3.81" y1="3.175" x2="3.81" y2="3.175" width="0.254" layer="21"/>
<wire x1="3.81" y1="3.175" x2="3.81" y2="1.27" width="0.254" layer="21"/>
<wire x1="3.81" y1="1.27" x2="3.81" y2="-3.175" width="0.254" layer="21"/>
<wire x1="3.81" y1="-3.175" x2="-3.81" y2="-3.175" width="0.254" layer="21"/>
<wire x1="-3.81" y1="-3.175" x2="-3.81" y2="1.27" width="0.254" layer="21"/>
<wire x1="-3.81" y1="1.27" x2="-3.81" y2="3.175" width="0.254" layer="21"/>
<wire x1="-3.81" y1="1.27" x2="3.81" y2="1.27" width="0.254" layer="21"/>
<pad name="1" x="-2.54" y="0" drill="1" shape="long" rot="R90"/>
<pad name="2" x="0" y="0" drill="1" shape="long" rot="R90"/>
<pad name="3" x="2.54" y="0" drill="1" shape="long" rot="R90"/>
<text x="-3.81" y="3.81" size="1.016" layer="25" ratio="10">&gt;NAME</text>
<text x="-3.81" y="-5.08" size="1.016" layer="27" ratio="10">&gt;VALUE</text>
</package>
<package name="22-23-2021">
<description>.100" (2.54mm) Center Headers - 2 Pin</description>
<wire x1="-2.54" y1="3.175" x2="2.54" y2="3.175" width="0.254" layer="21"/>
<wire x1="2.54" y1="3.175" x2="2.54" y2="1.27" width="0.254" layer="21"/>
<wire x1="2.54" y1="1.27" x2="2.54" y2="-3.175" width="0.254" layer="21"/>
<wire x1="2.54" y1="-3.175" x2="-2.54" y2="-3.175" width="0.254" layer="21"/>
<wire x1="-2.54" y1="-3.175" x2="-2.54" y2="1.27" width="0.254" layer="21"/>
<wire x1="-2.54" y1="1.27" x2="-2.54" y2="3.175" width="0.254" layer="21"/>
<wire x1="-2.54" y1="1.27" x2="2.54" y2="1.27" width="0.254" layer="21"/>
<pad name="1" x="-1.27" y="0" drill="1" shape="long" rot="R90"/>
<pad name="2" x="1.27" y="0" drill="1" shape="long" rot="R90"/>
<text x="-2.54" y="3.81" size="1.016" layer="25" ratio="10">&gt;NAME</text>
<text x="-2.54" y="-5.08" size="1.016" layer="27" ratio="10">&gt;VALUE</text>
</package>
</packages>
<symbols>
<symbol name="MV">
<wire x1="1.27" y1="0" x2="0" y2="0" width="0.6096" layer="94"/>
<text x="2.54" y="-0.762" size="1.524" layer="95">&gt;NAME</text>
<text x="-0.762" y="1.397" size="1.778" layer="96">&gt;VALUE</text>
<pin name="S" x="-2.54" y="0" visible="off" length="short" direction="pas"/>
</symbol>
<symbol name="M">
<wire x1="1.27" y1="0" x2="0" y2="0" width="0.6096" layer="94"/>
<text x="2.54" y="-0.762" size="1.524" layer="95">&gt;NAME</text>
<pin name="S" x="-2.54" y="0" visible="off" length="short" direction="pas"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="22-23-2031" prefix="X">
<description>.100" (2.54mm) Center Header - 3 Pin</description>
<gates>
<gate name="-1" symbol="MV" x="0" y="2.54" addlevel="always" swaplevel="1"/>
<gate name="-2" symbol="M" x="0" y="0" addlevel="always" swaplevel="1"/>
<gate name="-3" symbol="M" x="0" y="-2.54" addlevel="always" swaplevel="1"/>
</gates>
<devices>
<device name="" package="22-23-2031">
<connects>
<connect gate="-1" pin="S" pad="1"/>
<connect gate="-2" pin="S" pad="2"/>
<connect gate="-3" pin="S" pad="3"/>
</connects>
<technologies>
<technology name="">
<attribute name="MF" value="MOLEX" constant="no"/>
<attribute name="MPN" value="22-23-2031" constant="no"/>
<attribute name="OC_FARNELL" value="1462950" constant="no"/>
<attribute name="OC_NEWARK" value="30C0862" constant="no"/>
</technology>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="22-23-2021" prefix="X">
<description>.100" (2.54mm) Center Header - 2 Pin</description>
<gates>
<gate name="-1" symbol="MV" x="0" y="0" addlevel="always" swaplevel="1"/>
<gate name="-2" symbol="M" x="0" y="-2.54" addlevel="always" swaplevel="1"/>
</gates>
<devices>
<device name="" package="22-23-2021">
<connects>
<connect gate="-1" pin="S" pad="1"/>
<connect gate="-2" pin="S" pad="2"/>
</connects>
<technologies>
<technology name="">
<attribute name="MF" value="MOLEX" constant="no"/>
<attribute name="MPN" value="22-23-2021" constant="no"/>
<attribute name="OC_FARNELL" value="1462926" constant="no"/>
<attribute name="OC_NEWARK" value="25C3832" constant="no"/>
</technology>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
</libraries>
<attributes>
</attributes>
<variantdefs>
</variantdefs>
<classes>
<class number="0" name="default" width="1.016" drill="1.016">
<clearance class="0" value="1.27"/>
</class>
</classes>
<parts>
<part name="U$1" library="relay" deviceset="RELAY" device=""/>
<part name="U$2" library="relay" deviceset="RELAY" device=""/>
<part name="X1" library="con-molex" deviceset="22-23-2031" device=""/>
<part name="X2" library="con-molex" deviceset="22-23-2031" device=""/>
<part name="X3" library="con-molex" deviceset="22-23-2021" device=""/>
</parts>
<sheets>
<sheet>
<plain>
</plain>
<instances>
<instance part="U$1" gate="G$1" x="5.08" y="71.12" rot="R90"/>
<instance part="U$2" gate="G$1" x="43.18" y="71.12" rot="R90"/>
<instance part="X1" gate="-1" x="22.86" y="27.94" rot="R270"/>
<instance part="X1" gate="-2" x="20.32" y="27.94" rot="R270"/>
<instance part="X1" gate="-3" x="17.78" y="27.94" rot="R270"/>
<instance part="X2" gate="-1" x="22.86" y="96.52" rot="R90"/>
<instance part="X2" gate="-2" x="25.4" y="96.52" rot="R90"/>
<instance part="X2" gate="-3" x="27.94" y="96.52" rot="R90"/>
<instance part="X3" gate="-1" x="45.72" y="96.52" rot="R90"/>
<instance part="X3" gate="-2" x="48.26" y="96.52" rot="R90"/>
</instances>
<busses>
</busses>
<nets>
<net name="N$1" class="0">
<segment>
<pinref part="X1" gate="-3" pin="S"/>
<pinref part="U$1" gate="G$1" pin="IN"/>
<wire x1="17.78" y1="30.48" x2="-2.54" y2="30.48" width="0.1524" layer="91"/>
<wire x1="-2.54" y1="30.48" x2="-2.54" y2="66.04" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$2" class="0">
<segment>
<pinref part="U$2" gate="G$1" pin="IN"/>
<pinref part="X1" gate="-2" pin="S"/>
<wire x1="35.56" y1="66.04" x2="20.32" y2="66.04" width="0.1524" layer="91"/>
<wire x1="20.32" y1="66.04" x2="20.32" y2="30.48" width="0.1524" layer="91"/>
</segment>
</net>
<net name="GND" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="GND"/>
<wire x1="12.7" y1="66.04" x2="17.78" y2="66.04" width="0.1524" layer="91"/>
<wire x1="17.78" y1="66.04" x2="17.78" y2="50.8" width="0.1524" layer="91"/>
<pinref part="X1" gate="-1" pin="S"/>
<wire x1="17.78" y1="50.8" x2="22.86" y2="50.8" width="0.1524" layer="91"/>
<wire x1="22.86" y1="30.48" x2="22.86" y2="50.8" width="0.1524" layer="91"/>
<pinref part="U$2" gate="G$1" pin="GND"/>
<wire x1="22.86" y1="50.8" x2="50.8" y2="50.8" width="0.1524" layer="91"/>
<wire x1="50.8" y1="50.8" x2="50.8" y2="66.04" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$4" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="COM-OPEN"/>
<wire x1="-2.54" y1="73.66" x2="-2.54" y2="86.36" width="0.1524" layer="91"/>
<pinref part="X2" gate="-1" pin="S"/>
<wire x1="-2.54" y1="86.36" x2="22.86" y2="86.36" width="0.1524" layer="91"/>
<wire x1="22.86" y1="86.36" x2="22.86" y2="93.98" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$5" class="0">
<segment>
<pinref part="U$2" gate="G$1" pin="COM-OPEN"/>
<pinref part="X2" gate="-3" pin="S"/>
<wire x1="35.56" y1="73.66" x2="27.94" y2="73.66" width="0.1524" layer="91"/>
<wire x1="27.94" y1="73.66" x2="27.94" y2="93.98" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$6" class="0">
<segment>
<pinref part="X2" gate="-2" pin="S"/>
<wire x1="25.4" y1="93.98" x2="25.4" y2="81.28" width="0.1524" layer="91"/>
<wire x1="25.4" y1="81.28" x2="5.08" y2="81.28" width="0.1524" layer="91"/>
<pinref part="U$1" gate="G$1" pin="COM"/>
<wire x1="5.08" y1="81.28" x2="5.08" y2="78.74" width="0.1524" layer="91"/>
<pinref part="U$2" gate="G$1" pin="COM"/>
<wire x1="25.4" y1="81.28" x2="43.18" y2="81.28" width="0.1524" layer="91"/>
<wire x1="43.18" y1="81.28" x2="43.18" y2="78.74" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$3" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="COM-CLOSE"/>
<wire x1="12.7" y1="73.66" x2="25.4" y2="73.66" width="0.1524" layer="91"/>
<wire x1="25.4" y1="73.66" x2="25.4" y2="76.2" width="0.1524" layer="91"/>
<wire x1="25.4" y1="76.2" x2="40.64" y2="76.2" width="0.1524" layer="91"/>
<wire x1="40.64" y1="76.2" x2="40.64" y2="91.44" width="0.1524" layer="91"/>
<pinref part="X3" gate="-1" pin="S"/>
<wire x1="40.64" y1="91.44" x2="45.72" y2="91.44" width="0.1524" layer="91"/>
<wire x1="45.72" y1="91.44" x2="45.72" y2="93.98" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$7" class="0">
<segment>
<pinref part="U$2" gate="G$1" pin="COM-CLOSE"/>
<wire x1="50.8" y1="73.66" x2="48.26" y2="73.66" width="0.1524" layer="91"/>
<wire x1="48.26" y1="73.66" x2="48.26" y2="93.98" width="0.1524" layer="91"/>
<pinref part="X3" gate="-2" pin="S"/>
</segment>
</net>
</nets>
</sheet>
</sheets>
</schematic>
</drawing>
<compatibility>
<note version="6.3" minversion="6.2.2" severity="warning">
Since Version 6.2.2 text objects can contain more than one line,
which will not be processed correctly with this version.
</note>
</compatibility>
</eagle>
