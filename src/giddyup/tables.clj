(ns giddyup.tables
  (:use [hiccup.def :only [defelem]]))

(defelem thead
  [& headers]
  [:thead [:tr (map (partial vector :th) headers)]])

(defelem row
  [& cells]
  [:tr (map (partial vector :td) cells)])

(defelem tbody
  [& rows]
  [:tbody (map (partial apply row) rows)])

(defelem table
  [caption-or-headers & rows]
  (if-let [caption (if (string? caption-or-headers) caption-or-headers)]
    (let [[headers & rows] rows]
      [:table [:caption caption] (apply thead headers) (apply tbody rows)])
    [:table (apply thead caption-or-headers) (apply tbody rows)]))