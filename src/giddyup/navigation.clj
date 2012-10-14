(ns giddyup.navigation
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(declare dropdown-menu)

(defn- dropdown-menu-item
  "Returns a menu item. `item` can be one of:

  * `:divider`
  * `\"Nav header\"
  * `[\"link text\" \"/link-url\"]`
  * `[\"link text\" [submenu-items]]`"
  [item]
  (cond
   (keyword? item) [:li.divider]
   (string? item) [:li.nav-header item]
   :else (let [[s link-or-submenu] item]
           (if (string? link-or-submenu)
             [:li (html/link-to {:tabindex "-1"} link-or-submenu s)]
             [:li.dropdown-submenu
              (html/link-to {:tabindex "-1"} "#" s)
              (apply dropdown-menu link-or-submenu)]))))

(defn- nav-menu-item
  "Returns a navigation menu item. For format of `item` see `dropdown-menu-item`."
  [item]
  (if (keyword? item)
    [:li.divider-vertical]
    (let [[s link-or-submenu] item]
      (if (string? link-or-submenu)
        [:li (html/link-to link-or-submenu s)]
        [:li.dropdown
         (html/link-to {:class "dropdown-toggle" :data-toggle "dropdown"} "#"
                       s [:b.caret])
         (apply dropdown-menu link-or-submenu)]))))

(defelem dropdown-menu
  "Returns a dropdown menu consisting of `items`. For format of `items`, see
  `dropdown-menu-item`."
  [& items]
  [:ul.dropdown-menu {:role "menu" :aria-labelledby "dropdownMenu"}
   (map dropdown-menu-item items)])

(defelem nav-menu
  "Returns a navigation menu consisting of `items`. For format of `items`, see
  `dropdown-menu-item`."
  [& items]
  [:ul.nav (map nav-menu-item items)])