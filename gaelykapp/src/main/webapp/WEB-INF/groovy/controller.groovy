html.html {
  body {
    h3 "Despegar inteligente"
    form(method:"POST"){
      p{
        label "quiero conocer:"
        input(name:"target")
      }
      p{
        label "para la fecha:"
        input(name:"when")
      }
      p{
        label "y quedarme:"
        input(name:"how_long")
        label "días"
      }
      input(type:"submit")
    }
  }
}
