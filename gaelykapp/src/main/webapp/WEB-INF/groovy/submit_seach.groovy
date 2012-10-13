import com.google.appengine.api.datastore.Entity

Entity entity = new Entity("search_task") 

entity << params

entity.status = "initial"
entity.submited_date = new Date()
entity.finish_date = new Date() + 1

entity.save() 

def entities = datastore.execute{
  select all from search_task
}

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
