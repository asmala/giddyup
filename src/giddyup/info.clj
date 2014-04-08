(ns giddyup.info
  "Bootstrap elements for information display."
  (:use [hiccup.def :only [defelem]]))

(defelem alert
  "Returns an alert of the given `level` for `content`.

  ### Example

      (alert :danger \"Something went wrong.\")"
  [level & content]
  [:div {:class (str "alert fade in alert-" (name level))}
   [:button.close {:type "button" :data-dismiss "alert" :aria-hidden "true"}
    "×"]
   content])

(defelem icon
  "Returns an icon element of `type`.

  ### Example

      (icon :pencil)"
  [type]
  [:span {:class (str "glyphicon glyphicon-" (name type))}])

(defelem progress-bar
  "Returns a progress bar that's `percentage` complete with optional `content`.

  ### Example

      (progress-bar 60 \"60% Complete\")"
  [percentage & content]
  [:div.progress
   [:div.progress-bar {:style (str "width: " percentage "%;")
                       :role "progressbar" :aria-valuenow (str percentage)
                       :aria-valuemin "0" :aria-valuemax "100"}
    (if (empty? content)
      [:span.sr-only (str percentage "%")]
      content)]])
