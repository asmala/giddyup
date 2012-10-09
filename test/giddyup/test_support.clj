(ns giddyup.test-support
  (:require [hiccup.core :as hcore]
            [hiccup.compiler :as hcomp]))

(defn valid-hiccup?
  "Returns true if the result of the hiccup compilation is a string."
  [element]
  (string? (hcore/html element)))

(defn- normalize-nesting
  "Returns `coll` with nested seqs removed."
  [coll]
  (reverse (reduce #(if (seq? %2) (into %1 (normalize-nesting %2)) (conj %1 %2))
                   '() coll)))

(defn has-tag?
  "Returns false unless `node` contains a tag matching `match`."
  [node match]
  (let [[tag attrs content] (hcomp/normalize-element node)
        [tag-match attrs-match content-match] (hcomp/normalize-element match)
        content (normalize-nesting content)
        content-match (normalize-nesting content-match)]
    (or (and (or (= tag-match "*")
                 (= tag tag-match))
             (or (empty? attrs-match)
                 (every? #(or (nil? (attrs-match %))
                              (= (attrs %) (attrs-match %)))
                         (keys attrs-match)))
             (or (empty? content-match)
                 (= content content-match)))
        (some #(has-tag? % match)
              (filter sequential? content)))))

(defn has-tags?
  "Checks for multiple tags in node. Returns true if all tags were found."
  [node & matchers]
  (every? (partial has-tag? node) matchers))