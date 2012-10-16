
log.info "iniciando busqueda con params $params"

//static countries = [
	//	"CCS": "Caracas, Venezuela",
	//	"PAR": "Paris, Francia",
	//	"LON": "Londres, Inglaterra",
	//	"BCN": "Barcelona, España",
	//	"MAD": "Madrid, España",
	//	"LIS": "Lisboa, Portugal",
	//	"ROM": "Roma, Italia",
	//	"PCM": "Playa del carmen, Mexico"
	//]


log.info "Calculating prices"

def initial = new GregorianCalendar(2012, Calendar.NOVEMBER, 1, 23, 59)
def results = [:]
def lambda = 1
def daysBetween = 15
def range = 2
def lines = []
def show = { k,item ->
	//log.info "$k\t${item.stops}\t${item.price}\t${item.from}\t${item.to}\t${item.dest}"
	lines << "$k\t${item.stops}\t${item.price}\t${item.from}\t${item.to}\t${item.dest}"

}

def tasks = [:]
for(int i=0; i < 2; i++){
	for(String dest: [ params.target]){
		def from = initial.clone()
		from.add(Calendar.DATE, i)
		def to = from.clone()
		for(j in (lambda*-1)..lambda){
			daysBetween += j
			to.add(Calendar.DATE, daysBetween)
			def froms = String.format('%tF', from)
			def tos = String.format('%tF', to)
			def url = "http://www.despegar.com.ar/Flights.Services/Flights/Flights.svc/ClusteredFlights/BUE/${dest}/${froms}/${tos}/1/0/0"
			def fUrl = "http://www.despegar.com.ar/search/flights/RoundTrip/BUE/${dest}/${froms}/${tos}/1/0/0"
			if(!tasks[fUrl]){
				log.info "a $dest fecha: $froms volviendo el $tos..."
				tasks[fUrl] = {
					log.info "Starting thread"
					try{
						def searchResult = (url.toURL().getText()).parseJson()
						log.info "resultados obtenidos recorriendo..."
						results[fUrl] = searchResult.Boxs.collect({
							//log.info "DN price :${it.Itns[0].Tot?.Loc} class ${it.Itns[0].Tot?.Loc.getClass()}"
							[
								stops:it.Dep[0].Segmts?.Stops?.size(), 
								from:froms,
								to:tos,
								price:it.Itns[0].Tot?.Loc,
								dest:dest
                                                        ]
						})
						//results[fUrl].each{v ->
							//print "parcial: "
							//	show(fUrl,v)
							//}
					}catch(Exception e){
						log.info e
					}finally{
						log.info "ending thread"
					}
				}
			}
		}
	}
}

log.info "encolando ${tasks.keySet().size()} tareas"

tasks.each{k,task ->
	task()
}


done.await()

log.info "Imprimiendo Resultados"
results.each{k, v ->
	v.each{ item ->
		show(k,item)
	}
}
pool.shutdown()

mail.send(
	from: "dnoseda@gmail.com",
	to: "dnoseda@gmail.com",
	subject: "resultados",
	textBody: lines.join("\n")
)

