(ns giddyup.info
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(defelem alert
  "Returns an alert for `content`. Note that you can pass additional CSS classes
  to `alert`, e.g. (alert {:class \"alert alert-error\"} \"Login failed\")."
  [& content]
  [:div.alert
   (html/link-to {:class "close" :data-dismiss "alert"} "#" "×")
   content])

;; Graciously stolen from https://github.com/weavejester/hiccup-bootstrap
(defelem icon
  "Returns an icon element of `type`."
  [type]
  [:i {:class (str "icon-" (name type))}])

(defelem progress-bar
  "Returns a progress bar that's `percentage` complete."
  [percentage]
  [:div.progress
   [:div.bar {:style (str "width: " percentage "%;")}]])