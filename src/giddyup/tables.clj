(ns ^{:doc "Bootstrap table markup."}
  giddyup.tables
  (:use [hiccup.def :only [defelem]]))

(defelem thead
  "Returns a table header section for `headers`.

  ### Example

      (thead \"Name\" \"Email\")"
  [& headers]
  [:thead [:tr (for [h headers] [:th h])]])

(defelem row
  "Returns a table row for `cells`.

  ### Example

      (row \"Joe\" \"joe@example.com\")"
  [& cells]
  [:tr (for [c cells] [:td c])])

(defelem tbody
  "Returns a table body with `rows` passed through `row`.

  ### Example

      (tbody [\"Joe\" \"joe@example.com\"]
             [\"Jill\" \"jill@example.com\"])"
  [& rows]
  [:tbody (map (partial apply row) rows)])

(defelem table
  "Returns a table with optional caption, header row, and body rows.

  ### Example

      (table \"Contact Info\"
             [\"Name\" \"Email\"]
             [\"Joe\" \"joe@example.com\"]
             [\"Jill\" \"jill@example.com\"])"
  [caption-or-headers & rows]
  (if-let [caption (if (string? caption-or-headers) caption-or-headers)]
    (let [[headers & rows] rows]
      [:table [:caption caption] (apply thead headers) (apply tbody rows)])
    [:table (apply thead caption-or-headers) (apply tbody rows)]))