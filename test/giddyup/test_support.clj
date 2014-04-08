(ns giddyup.test-support
  (:use [clojure.test])
  (:require [clojure.java.io :as io]
            [clojure.walk :as walk]
            [hiccup.util :as util]
            [net.cgrand.enlive-html :as enlive]))

(defn- normalize-nodes
  "Walks through a tree of Enlive nodes and normalizes them for comparison."
  [m]
  (let [f (fn [[k v]]
            (cond
             (and (= k :content) (or (nil? v) (= v ""))) [k '()]
             (and (= k :attrs) (nil? v)) [k {}]
             (= k :bootstrap-template) nil
             (#{:href :src} k) [k (util/as-str v)]
             :default [k v]))]
    ;; only apply to maps
    (walk/postwalk (fn [x] (if (map? x)
                            (into {} (map f x))
                            x))
                   m)))

(def bootstrap-html
  (enlive/html-resource (io/resource "components.html")))

(defn enlive-html
  "Converts `hiccup-data` into Enlive nodes."
  [hiccup-data]
  (-> (enlive/html hiccup-data)
      first
      normalize-nodes))

(defn bootstrap-template
  "Returns nodes corresponding to `template` in `test-resources/components.html`."
  [template]
  (-> (enlive/select bootstrap-html
                     [(enlive/attr= :bootstrap-template (name template))])
      first
      normalize-nodes))

(defmacro matches-template?
  "Tests if the given `hiccup-data` matches the Bootstrap `template`."
  [template hiccup-data]
  `(is (= (enlive-html ~hiccup-data)
          (bootstrap-template ~template))))
