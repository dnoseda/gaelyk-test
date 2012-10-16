
get "/", forward: "/WEB-INF/pages/index.gtpl"
get "/datetime", forward: "/datetime.groovy"

get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"

post "/do_search", forward: "/do_search.groovy"
post "/send_me", forward: "/send_me.groovy"
