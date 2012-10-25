(ns ^{:doc "Bootstrap display elements."}
  giddyup.display
  (:use [giddyup.util :only [anchor]]
        [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(defn- accordion-group
  "Creates accordion group number `n` inside accordion `parent-id` with the
  given `title` and `content`."
  [n parent-id title & content]
  (let [id (str parent-id "-" n)
        body-class "accordion-body collapse"
        body-class (if (zero? n) (str body-class " in") body-class)]
    [:div.accordion-group
     [:div.accordion-heading
      (html/link-to {:class "accordion-toggle" :data-toggle "collapse"
                     :data-parent (anchor parent-id)} (anchor id)
                     title)]
     [:div {:id id :class body-class}
      [:div.accordion-inner
       content]]]))

(defelem accordion
  "Creates an accordion element with the given `id`. The format for the `groups`
  is `[title & content]`.

  ### Example

      (accordion \"myAccordion\"
                 [\"Introduction\"
                  [:p \"Here's some things you need to know.\"]]
                 [\"More info\"
                  [:p \"You might also find this interesting.\"]])"
  [id & groups]
  [:div.accordion {:id id}
   (map-indexed #(apply accordion-group %1 id %2) groups)])

(defn- slide
  "Wraps `content` in a slide, and marks it as active if `active?` is true."
  [active? content]
  [:div {:class (if active? "item active" "item")}
   content])

(defelem carousel
  "Creates a carousel element with the given `id`. `slides` will be wrapped in
  `[:div.item]`.

  ### Example

      (carousel \"myCarousel\"
                [(image \"tibet.png\")
                 [:div.caption [:h4 \"Top of the world\"]]]
                [(image \"lhasa.png\")
                 [:div.caption [:h4 \"Off to Potala palace\"]]])"
  [id & slides]
  [:div.carousel.slide {:id id}
   [:div.carousel-inner
    (map-indexed #(slide (zero? %1) %2) slides)]
   (html/link-to {:class "carousel-control left" :data-slide "prev"}
                 (anchor id) "‹")
   (html/link-to {:class "carousel-control right" :data-slide "next"}
                 (anchor id) "›")])