(ns giddyup.page
  "Bootstrap page templates."
  (:use [hiccup.page]))

(defn include-bootstrap-css
  "Returns an include tag for Bootstrap CSS, loaded from a CDN."
  []
  (include-css "https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"))

(defn include-bootstrap-js
  "Returns script tags for jQuery and Bootstrap JavaScript, loaded from a CDN."
  []
  (include-js
   "https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"
   "https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"))

(defn bootstrap-meta
  "Returns the standard Bootstrap meta tags."
  []
  (list
   [:meta {:charset "utf-8"}]
   [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]))

(defn bootstrap-polyfills
  "Returns script tags for IE polyfills."
  []
  (list
   "<!--[if lt IE 9]>"
   (include-js
    "https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"
    "https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js")
   "<![endif]-->"))

(defn head
  "Returns a head element with a Bootstrap set-up, the given `title`, and
  optional additional `content`.

  ### Example

      (head \"Giddyup Documentation\"
            [:meta {:name \"author\" :content \"Janne Asmala\"}])"
  [title & content]
  [:head
   (bootstrap-meta)
   [:title title]
   (include-bootstrap-css)
   (bootstrap-polyfills)
   content])

(defn page
  "Returns a Bootstrap page template with the given `title` and content,
  including all necessary CSS and JavaScript includes. The primary use case of
  this function is rapid prototyping since customization options are limited.

  ### Example

      (page \"Giddyup Documentation\"
            [:div.page-header [:h1 \"Welcome!\"]])"
  [title & content]
  (html5
   (head title)
   [:body
    content
    (include-bootstrap-js)]))
