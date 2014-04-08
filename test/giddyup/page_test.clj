(ns giddyup.page-test
  (:use [giddyup.page]
        [hiccup.util]
        [clojure.test])
  (:require [hiccup.page :as hiccup]))

(def bootstrap-resources
  {:css "https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
   :html5shiv "https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"
   :jquery "https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"
   :js "https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"
   :respond "https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"})

(deftest test-include-bootstrap-css
  (testing "bootstrap-css-test"
    (is (= (include-bootstrap-css)
           (list
            [:link {:href (to-uri (bootstrap-resources :css))
                    :type "text/css" :rel "stylesheet"}])))))

(deftest test-include-bootstrap-js-test
  (testing "bootstrap-js-test"
    (is (= (include-bootstrap-js)
           (list
            [:script {:src (to-uri (bootstrap-resources :jquery))
                      :type "text/javascript"}]
            [:script {:src (to-uri (bootstrap-resources :js))
                      :type "text/javascript"}])))))

(deftest test-bootstrap-met
  (testing "bootstrap-meta"
    (is (= (bootstrap-meta)
           (list
            [:meta {:charset "utf-8"}]
            [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
            [:meta {:name "viewport"
                    :content "width=device-width, initial-scale=1"}])))))

(deftest test-bootstrap-polyfills
  (testing "bootstrap-polyfills"
    (is (= (bootstrap-polyfills)
           (list
            "<!--[if lt IE 9]>"
            (list
             [:script {:src (to-uri (bootstrap-resources :html5shiv))
                       :type "text/javascript"}]
             [:script {:src (to-uri (bootstrap-resources :respond))
                       :type "text/javascript"}])
            "<![endif]-->")))))

(deftest test-head
  (testing "head"
    (is (= (head "Giddyup Documentation"
                 [:meta {:name "author" :content "Janne Asmala"}])
           [:head
            (bootstrap-meta)
            [:title "Giddyup Documentation"]
            (include-bootstrap-css)
            (bootstrap-polyfills)
            '([:meta {:name "author" :content "Janne Asmala"}])]))))

(deftest test-page
  (testing "page"
    (is (= (page "Giddyup Documentation"
                 [:div.page-header [:h1 "Welcome"]])
           (hiccup/html5
            (head "Giddyup Documentation")
            [:body
             [:div.page-header [:h1 "Welcome"]]
             (include-bootstrap-js)])))))
