<%

def query = new Query("search_task")
 
PreparedQuery preparedQuery = datastore.prepare(query)
 
def entities = preparedQuery.asList()

%>
<table>
  <thead>
    <tr>
      <td>Destino</td>
      <td>Fecha</td>
      <td>Días</td>
      <td>Estado</td>
    </tr>
  </thead>
  <tbody>
    <% entities.each -> task %>
    <tr>
      <td>${task.target}</td>
      <td>${task.when}</td>
      <td>${task.how_long}</td>
      <td>${task.status}</td>
    </tr>
  </tbody>
</table>
