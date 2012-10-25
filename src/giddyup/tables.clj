(ns giddyup.tables
  (:use [hiccup.def :only [defelem]]))

(defelem thead
  "Returns a table header section for `headers`."
  [& headers]
  [:thead [:tr (for [h headers] [:th h])]])

(defelem row
  "Returns a table row for `cells`."
  [& cells]
  [:tr (for [c cells] [:td c])])

(defelem tbody
  "Returns a table body with `rows` passed through `row`."
  [& rows]
  [:tbody (map (partial apply row) rows)])

(defelem table
  "Returns a table with optional caption, header row, and body rows."
  [caption-or-headers & rows]
  (if-let [caption (if (string? caption-or-headers) caption-or-headers)]
    (let [[headers & rows] rows]
      [:table [:caption caption] (apply thead headers) (apply tbody rows)])
    [:table (apply thead caption-or-headers) (apply tbody rows)]))