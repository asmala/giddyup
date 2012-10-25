(ns ^{:doc "Bootstrap elements for information display."}
  giddyup.info
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(defelem alert
  "Returns an alert for `content`. Note that you can pass additional CSS classes
  to `alert`, e.g. (alert {:class \"alert alert-error\"} \"Login failed\").

  ### Example

      (alert \"Something went wrong.\")"
  [& content]
  [:div.alert
   (html/link-to {:class "close" :data-dismiss "alert"} "#" "×")
   content])

;; Graciously stolen from https://github.com/weavejester/hiccup-bootstrap
(defelem icon
  "Returns an icon element of `type`.

  ### Example

      (icon :pencil)"
  [type]
  [:i {:class (str "icon-" (name type))}])

(defelem progress-bar
  "Returns a progress bar that's `percentage` complete.

  ### Example

      (progress-bar 60)"
  [percentage]
  [:div.progress
   [:div.bar {:style (str "width: " percentage "%;")}]])