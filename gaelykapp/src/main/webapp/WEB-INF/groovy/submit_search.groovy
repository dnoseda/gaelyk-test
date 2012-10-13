import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
 

Entity entity = new Entity("search_task") 

entity << params

entity.status = "initial"
entity.submited_date = new Date()
entity.finish_date = new Date() + 1

entity.save() 

// query the scripts stored in the datastore
// "savedscript" corresponds to the entity table containing the scripts' text
def query = new Query("search_task")
 
PreparedQuery preparedQuery = datastore.prepare(query)
 
// return only the first 10 results
def entities = preparedQuery.asList( withLimit(10) )

//def entities = datastore.execute{
//  select all from search_task
//}

html.html{
  body{
    h3 "Lista de busquedas"
    table{
      thead{
        tr{
          td "Destino"
          td "Fecha"
          td "Días"
          td "Estado"
        }
      }
      tbody{
        entities.each{
          tr{
            td it.target
            td it.when
            td it.how_long
            td it.status
          }
        }
      }
    }
  }
}
