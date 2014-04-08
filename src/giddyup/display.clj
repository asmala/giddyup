(ns giddyup.display
  "Bootstrap display elements."
  (:use [hiccup.def :only [defelem]])
  (:require [giddyup.info :as info]
            [hiccup.element :as html]))

(defn- accordion-group
  "Creates accordion group number `n` inside accordion `parent-id` with the
  given `title` and `content`."
  [parent-id n title & content]
  (let [id (str parent-id "-" n)]
    [:div.panel.panel-default
     [:div.panel-heading
      [:h4.panel-title
       [:a {:data-toggle "collapse" :data-parent (str "#" parent-id)
            :href (str "#" id)}
        title]]]
     [:div {:id id :class (str "panel-collapse collapse"
                               (if (zero? n) " in"))}
      [:div.panel-body
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
  [:div.panel-group {:id id}
   (map-indexed (partial apply accordion-group id) groups)])

(defelem carousel
  "Creates a carousel element with the given `id`. `slides` will be wrapped in
  `[:div.item]`, and `slide` in `[:div.item.active]`.

  ### Example

      (carousel \"myCarousel\"
                [(image \"tibet.png\")
                 [:div.caption [:h4 \"Top of the world\"]]]
                [(image \"lhasa.png\")
                 [:div.caption [:h4 \"Off to Potala palace\"]]])"
  [id [img caption] & slides]
  [:div.carousel.slide {:id id :data-ride "carousel"}
   [:ol.carousel-indicators
    (for [n (range (inc (count slides)))]
      [:li (merge {:data-target (str "#" id) :data-slide-to (str n)}
                  (if (zero? n) {:class "active"}))])]
   [:div.carousel-inner
    [:div.item.active img [:div.carousel-caption caption]]
    (for [[img caption] slides]
      [:div.item img [:div.carousel-caption caption]])]
   [:a {:class "carousel-control left" :href (str "#" id) :data-slide "prev"}
    (info/icon "chevron-left")]
   [:a {:class "carousel-control right" :href (str "#" id) :data-slide "next"}
    (info/icon "chevron-right")]])

(defelem media-object
  "Returns a media object. `image` can be either a string representing a URL or
  a vector representing a Hiccup element. `heading` can similarly be either a
  string representing the media object heading or a vector representation of the
  heading element.

  ### Examples

      (media-object \"/img/avatars/joe.png\" \"On Nov 1st, 2012, Joe said:\"
                    \"Media objects are neat!\")

      (media-object
       (image {:class \"pull-right \"media-object\"} \"/img/admin.png\")
       [:h4.media-heading.admin \"On Nov 2nd 2012, Admin said:]
       [:strong \"Yes, they were added in Bootstrap 2.2.0\"])"
  [image heading & content]
  [:div.media
   (if (vector? image)
     image
     (html/image {:class "pull-left media-object"} image))
   [:div.media-body
    (if (vector? heading)
      heading
      [:h4.media-heading heading])
    content]])
