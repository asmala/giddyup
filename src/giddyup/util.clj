(ns ^{:doc "Utility functions for the Giddyup library."}
  giddyup.util
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(defn anchor
  "Converts `maybe-id` into an anchor by prepending \"#\" if missing."
  [maybe-id]
  (if (= \# (first maybe-id))
    maybe-id
    (str "#" maybe-id)))

(defn css-classes
  "Creates \"namespaced\" CSS class names according to the Bootstrap convention.

  Aliased as `c`.

  ### Example:

      (css-classes \"btn\" [\"success\" \"large\"])
      ;=> \"btn btn-success btn-large\""
  [parent children]
  (apply str parent (map (partial str " " parent "-") children)))

(def c css-classes)

(defn css-attr-map
  "Calls `css-classes` and inserts the results into a map as the value for the
  `:class` key.

  Can be passed optional `attrs`, which are merged with the resulting map. If
  `attrs` contains values for `:class`, these are concatenated with the results
  from `css-classes`.

  Aliased as `cm`."
  ([parent children attrs]
     (merge-with #(str %1 " " %2) (css-attr-map parent children) attrs))
  ([parent children]
     {:class (css-classes parent children)}))

(def cm css-attr-map)