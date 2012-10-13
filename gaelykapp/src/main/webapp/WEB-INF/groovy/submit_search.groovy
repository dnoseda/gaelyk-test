import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
 

Entity entity = new Entity("search_task") 

entity << params

entity.status = "initial"
entity.submited_date = new Date()
entity.finish_date = new Date() + 1

def res = entity.save() 

// query the scripts stored in the datastore
// "savedscript" corresponds to the entity table containing the scripts' text

//def entities = datastore.execute{
//  select all from search_task
//}

forward 'WEB-INF/pages/tasks.gtpl'
