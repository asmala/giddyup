(defproject giddyup "0.6.2"
  :description "Hiccup library for generating Bootstrap elements."
  :url "https://github.com/asmala/giddyup"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[hiccup "1.0.5"]]
  :profiles {:1.3  {:dependencies [[org.clojure/clojure "1.3.0"]]}
             :1.4  {:dependencies [[org.clojure/clojure "1.4.0"]]}
             :1.5  {:dependencies [[org.clojure/clojure "1.5.1"]]}
             :1.6  {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :dev  {:plugins [[codox "0.6.7"]]}}
  :aliases {"test-all" ["with-profile" "test:test,1.3:test,1.4:test,1.5:test,1.6" "test"]}
  ::min-lein-version "2.0.0")
