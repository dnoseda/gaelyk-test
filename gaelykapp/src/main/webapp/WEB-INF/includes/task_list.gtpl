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
    <% request.entities.each {task -> %>
    <tr>
      <td>${task.target}</td>
      <td>${task.when}</td>
      <td>${task.how_long}</td>
      <td>${task.status}</td>
    </tr>
    <% } %>
  </tbody>
</table>
