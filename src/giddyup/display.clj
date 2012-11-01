(ns giddyup.display
  "Bootstrap display elements."
  (:use [giddyup.util :only [anchor]]
        [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(defn- accordion-group
  "Creates accordion group number `n` inside accordion `parent-id` with the
  given `title` and `content`."
  [parent-id n title & content]
  (let [id (str parent-id "-" n)
        body-class (str "accordion-body collapse"
                        (if (zero? n) " in"))]
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
  [id slide & slides]
  [:div.carousel.slide {:id id}
   [:div.carousel-inner
    [:div.item.active slide]
    (for [slide slides] [:div.item slide])]
   (html/link-to {:class "carousel-control left" :data-slide "prev"}
                 (anchor id) "‹")
   (html/link-to {:class "carousel-control right" :data-slide "next"}
                 (anchor id) "›")])

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

(defelem thumbnails
  "Returns a set of thumbnails. `thumbnail-class` will be applied to the element
  wrapping each thumbnail and should indicate the width of the thumbnails, e.g.
  `\"span4\"`. `images` are of the format `[src alt? url?]`.

  ### Example

      (thumbnails \"span4\"
                  [\"tibet.png\" \"Top of the world\"]
                  [\"potala.png\" \"Potala Palace\" \"#potala\"])"
  [thumbnail-class & images]
  [:ul.thumbnails
   (for [[src alt url] images]
     [:li {:class thumbnail-class}
      (if url
        (html/link-to {:class "thumbnail"} url
                      (html/image src alt))
        [:div.thumbnail (html/image src alt)])])])