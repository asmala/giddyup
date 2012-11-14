(ns giddyup.navigation
  "Bootstrap navigation elements."
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(defelem navbar
  "Returns a navigation bar with `content`.

  ### Example

      (navbar (link-to {:class \"brand\"} \"/\" \"My Journal\"))"
  [& content]
  [:div.navbar
   [:div.navbar-inner
    [:div.container
     content]]])

(defelem dropdown-menu
  "Returns a dropdown menu consisting of `items`. For format of `items`, see
  `dropdown-menu-item`.

  ### Item format

  `items` can be one of:

  * `:divider`
  * `\"Nav header\"`
  * `[\"link text\" \"/link-url\"]`
  * `[\"link text\" [submenu-items]]`

  ### Example

      (dropdown-menu [\"Add new\" \"/media/new\"]
                     :divider
                     \"View old media\"
                     [\"Photos\" \"/media/photos\"]
                     [\"Video\" \"/media/video\"])"
  [& items]
  [:ul.dropdown-menu {:role "menu" :aria-labelledby "dropdownMenu"}
   (for [item items]
     (cond
      (keyword? item) [:li.divider]
      (string? item) [:li.nav-header item]
      :else (let [[s link-or-submenu] item]
              (if (string? link-or-submenu)
                [:li (html/link-to {:tabindex "-1"} link-or-submenu s)]
                [:li.dropdown-submenu
                 (html/link-to {:tabindex "-1"} "#" s)
                 (apply dropdown-menu link-or-submenu)]))))])

(defelem nav-menu
  "Returns a navigation menu consisting of `items`. For format of `items`, see
  `dropdown-menu-item`.

  ### Item format

  For format of `items`, see `dropdown-menu`.

  ### Example

      (nav-menu [\"Journal Entries\" \"/journal-entries\"]
                :divider
                [\"Media\" [[\"Add new\" \"/media/new\"]
                           :divider
                           [\"Photos\" \"/media/photos\"]
                           [\"Video\" \"/media/video\"]]])"
  [& items]
  [:ul.nav
   (for [item items]
       (if (keyword? item)
         [:li.divider-vertical]
         (let [[s link-or-submenu] item]
           (if (string? link-or-submenu)
             [:li (html/link-to link-or-submenu s)]
             [:li.dropdown
              (html/link-to {:class "dropdown-toggle" :data-toggle "dropdown"} "#"
                            s [:b.caret])
              (apply dropdown-menu link-or-submenu)]))))])

(defelem breadcrumbs
  "Returns a breadcrumbs link element. The last item in `links` should be the
  title of the current page.

  ### Example

      (breadcrumbs (link-to \"/\" \"Home\")
                   (link-to \"/news\" \"News\")
                   \"Politics\")"
  [& links]
  [:ul.breadcrumb
   (for [link (butlast links)]
     [:li link " " [:span.divider "/"]])
   [:li.active (last links)]])

(defelem pager
  "Returns a pager element. Disables the previous link and/or the next link if
  passed strings for `prev-link` or `next-link`.

  ### Example

      (pager \"Previous\" (link-to \"?page=2\" \"Next\"))"
  [prev-link next-link]
  (letfn [(pager-link [link css-class]
            (if (string? link)
              [:li {:class (str css-class " disabled")} (html/link-to "#" link)]
              [:li {:class css-class} link]))]
    [:ul.pager
     (pager-link prev-link "previous")
     (pager-link next-link "next")]))