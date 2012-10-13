
// routes for the blobstore service example
get "/upload",  forward: "/upload.gtpl"
get "/success", forward: "/success.gtpl"
get "/failure", forward: "/failure.gtpl"

get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"
get "/firsto", forward: "/controller.groovy"
post "/firsto", forward: "/submit_search.groovy"
