import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
 

Entity entity = new Entity("search_task") 

entity << params

entity.status = "initial"
entity.submited_date = new Date()
entity.finish_date = new Date() + 1

def res = entity.save() 

def query = new Query("search_task")
 
PreparedQuery preparedQuery = datastore.prepare(query)
 
request.entities = preparedQuery.asList(withDefaults())

forward 'WEB-INF/pages/tasks.gtpl'
