(ns giddyup.navigation
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(declare dropdown-menu)

(defn menu-item
  "Returns a menu item. `type` can be either `:dropdown` or `:nav`. `menu-item`
  can be one of:

  * `:divider`
  * `[\"link text\" \"/link-url\"]`
  * `[\"link text\" [submenu-items]]`"
  [type item]
  (let [dropdown? (= type :dropdown)]
    (if (keyword? item)
      [:li {:class (if dropdown? "divider" "divider-vertical")}]
      [:li
       (let [[s link-or-submenu] item
             link-args (if dropdown? {:tabindex "-1"} {})
             menu-args (if dropdown? {:class "dropdown-submenu"} {})]
         (if (string? link-or-submenu)
           (html/link-to link-args link-or-submenu s)
           (list
            (html/link-to link-args "#" s)
            (apply dropdown-menu menu-args link-or-submenu))))])))

(defelem dropdown-menu
  "Returns a dropdown menu consisting of `items`. For format of `items`, see
  `menu-item`."
  [& items]
  [:ul.dropdown-menu {:role "menu" :aria-labelledby "dropdownMenu"}
   (map (partial menu-item :dropdown) items)])

(defelem nav-menu
  "Returns a navigation menu consisting of `items`. For format of `items`, see
  `menu-item`."
  [& items]
  [:ul.nav (map (partial menu-item :nav) items)])