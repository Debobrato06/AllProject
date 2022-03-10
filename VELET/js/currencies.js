var Currency = {
  rates: {"USD":1.0,"EUR":1.1271,"GBP":1.25762,"CAD":0.767518,"ARS":0.0240471,"AUD":0.702041,"BRL":0.267617,"CLP":0.00147151,"CNY":0.145349,"CYP":0.397899,"CZK":0.0440374,"DKK":0.151014,"EEK":0.0706676,"HKD":0.127808,"HUF":0.00345854,"ISK":0.00793368,"INR":0.0145831,"JMD":0.00745379,"JPY":0.00926736,"LVL":1.57329,"LTL":0.320236,"MTL":0.293496,"MXN":0.052655,"NZD":0.669213,"NOK":0.117232,"PLN":0.263874,"SGD":0.736613,"SKK":21.5517,"SIT":175.439,"ZAR":0.0715852,"KRW":0.000850084,"SEK":0.106765,"CHF":1.01606,"TWD":0.0321718,"UYU":0.0284317,"MYR":0.243102,"BSD":1.0,"CRC":0.00171394,"RON":0.23831,"PHP":0.0195517,"AED":0.272294,"VEB":0.000100125,"IDR":7.14086e-05,"TRY":0.174895,"THB":0.0323243,"TTD":0.147367,"ILS":0.281548,"SYP":0.00194072,"XCD":0.370515,"COP":0.000313387,"RUB":0.0158693,"HRK":0.152179,"KZT":0.00260766,"TZS":0.000435434,"XPT":831.29,"SAR":0.266667,"NIO":0.0299103,"LAK":0.000114696,"OMR":2.60078,"AMD":0.00209784,"CDF":0.000608754,"KPW":0.00111155,"SPL":6.0,"KES":0.00972292,"ZWD":0.00276319,"KHR":0.000245339,"MVR":0.0638163,"GTQ":0.129963,"BZD":0.496229,"BYR":4.91401e-05,"LYD":0.716384,"DZD":0.00833771,"BIF":0.000542617,"GIP":1.25762,"BOB":0.144196,"XOF":0.00171825,"STD":4.59765e-05,"NGN":0.00278187,"PGK":0.29542,"ERN":0.0666667,"MWK":0.00130062,"CUP":0.0377358,"GMD":0.0200496,"CVE":0.0102213,"BTN":0.0145831,"XAF":0.00171825,"UGX":0.000266668,"MAD":0.104647,"MNT":0.000378573,"LSL":0.0715852,"XAG":15.218,"TOP":0.428198,"SHP":1.25762,"RSD":0.009579,"HTG":0.0106678,"MGA":0.000276015,"MZN":0.0161133,"FKP":1.25762,"BWP":0.0943039,"HNL":0.0408801,"PYG":0.000166464,"JEP":1.25762,"EGP":0.0602315,"LBP":0.00066335,"ANG":0.559441,"WST":0.375501,"TVD":0.702041,"GYD":0.00476228,"GGP":1.25762,"NPR":0.00907193,"KMF":0.002291,"IRR":2.37953e-05,"XPD":1545.45,"SRD":0.13468,"TMM":5.71429e-05,"SZL":0.0715852,"MOP":0.124085,"BMD":1.0,"XPF":0.0094451,"ETB":0.0344289,"JOD":1.41044,"MDL":0.0565611,"MRO":0.00271739,"YER":0.00400561,"BAM":0.576277,"AWG":0.558659,"PEN":0.304599,"VEF":0.100125,"SLL":0.00010929,"KYD":1.22002,"AOA":0.00289093,"TND":0.34662,"TJS":0.105936,"SCR":0.0714541,"LKR":0.00568307,"DJF":0.00561798,"GNF":0.000108554,"VUV":0.0087321,"SDG":0.0221675,"IMP":1.25762,"GEL":0.35213,"FJD":0.466201,"DOP":0.0196387,"XDR":1.38326,"MUR":0.0276242,"MMK":0.000662761,"LRD":0.00500075,"BBD":0.5,"ZMK":7.9681e-05,"XAU":1415.5,"VND":4.28082e-05,"UAH":0.0386623,"TMT":0.285714,"IQD":0.000839715,"BGN":0.576277,"KGS":0.0143733,"RWF":0.00111689,"BHD":2.65957,"UZS":0.000116509,"PKR":0.00630889,"MKD":0.0182849,"AFN":0.0124689,"NAD":0.0715852,"BDT":0.0118322,"AZN":0.588237,"SOS":0.00171109,"QAR":0.274725,"PAB":1.0,"CUC":1.0,"SVC":0.114286,"SBD":0.121,"ALL":0.00922505,"BND":0.736613,"KWD":3.2787,"GHS":0.188377,"ZMW":0.0796809,"XBT":10735.0,"NTD":0.0337206,"BYN":0.491401,"CNH":0.145376,"MRU":0.0271739,"STN":0.0459765,"VES":0.000139947},
  convert: function(amount, from, to) {
    return (amount * this.rates[from]) / this.rates[to];
  }
};
