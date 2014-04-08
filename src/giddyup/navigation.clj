(ns giddyup.navigation
  "Bootstrap navigation elements."
  (:use [hiccup.def :only [defelem]]))

(defelem collapse-toggle
  "Returns a collapse toggle button for use in navbars."
  ([target] (collapse-toggle target "Toggle navigation"))
  ([target alt-text]
     [:button.navbar-toggle {:type "button" :data-toggle "collapse"
                             :data-target target}
      [:span.sr-only alt-text]
      [:span.icon-bar]
      [:span.icon-bar]
      [:span.icon-bar]]))

(defelem navbar
  "Returns a navigation bar with `content`.

  ### Example

      (navbar
        [:div.navbar-header
         (link-to {:class \"brand\"} \"/\" \"My Journal\")
         (collapse-toggle \"#main-nav\")])"
  [& content]
  [:nav.navbar.navbar-default {:role "navigation"}
   [:div.container
    content]])

(defelem dropdown-menu
  "Returns a dropdown menu consisting of `items`. For format of `items`, see
  `dropdown-menu-item`.

  ### Item format

  `items` can be one of:

  * `:divider`
  * `\"Nav header\"`
  * `[\"link text\" \"/link-url\"]`

  ### Example

      (dropdown-menu [\"Add new\" \"/media/new\"]
                     :divider
                     \"View old media\"
                     [\"Photos\" \"/media/photos\"]
                     [\"Video\" \"/media/video\"])"
  [& items]
  [:ul.dropdown-menu {:role "menu"}
   (for [item items]
     (cond
      (keyword? item) [:li.divider {:role "presentation"}]
      (string? item) [:li.dropdown-header {:role "presentation"} item]
      :default (let [[title href] item]
                 [:li {:role "presentation"}
                  [:a {:tabindex "-1" :role "menuitem" :href href} title]])))])

(defelem pager
  "Returns a pager element. Disables the previous link and/or the next link if
  passed strings for `prev-link` or `next-link`.

  ### Example

      (pager \"Previous\" (link-to \"?page=2\" \"Next\"))"
  [prev-link next-link]
  (letfn [(pager-link [link css-class]
            (if (string? link)
              [:li {:class (str css-class " disabled")}
               [:a {:href "#"} link]]
              [:li {:class css-class} link]))]
    [:ul.pager
     (pager-link prev-link "previous")
     (pager-link next-link "next")]))
