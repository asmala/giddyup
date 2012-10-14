(ns giddyup.util
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]))

(defelem alert
  "Returns an alert for `content`. Note that you can pass additional CSS classes
  to `alert`, e.g. (alert {:class \"alert alert-error\"} \"Login failed\")."
  [& content]
  [:div.alert
   (html/link-to {:class "close" :data-dismiss "alert"} "#" "×")
   content])