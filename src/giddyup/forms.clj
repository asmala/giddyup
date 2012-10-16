(ns giddyup.forms
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]
            [hiccup.form :as f]))

(defelem wrapper
  "Returns `label` and `inputs` as well as eventual `error` and `hint`
  inside a wrapper element."
  ([label input] (wrapper label nil nil input))
  ([label error hint & inputs]
     [:div.control-group (if error {:class "control-group error"})
      label
      [:div.controls
       inputs
       error
       hint]]))

(defelem actions
  "Returns the `content` in a wrapper intended for form buttons."
  [& content]
  [:div.form-actions content])

(defelem submit-button
  "Returns a submit-button with `s` as text."
  [s]
  (f/submit-button {:class "btn btn-primary"} s))

(defelem button-link-to
  "Returns a button-like link for `url` with `content` as text."
  [url & content]
  (html/link-to {:class "btn"} url content))

(defelem label
  "Returns a label element for the input called `name` with `content` as text."
  [name & content]
  (f/label {:class "control-label"} name content))

(defelem error
  "Returns an error element with `content` as text."
  [& content]
  [:span.help-inline content])

(defelem hint
  "Returns a textual hint element with `content` as text."
  [& content]
  [:p.help-block content])

(defelem fieldset
  "Returns a fieldset wrapped around `content` with `s` as legend."
  [s & content]
  [:fieldset
   [:legend s]
   content])

(defelem check-box
  "Returns a checkbox element."
  ([name] (check-box name false))
  ([name checked?]
     (f/check-box name checked?)))

(defelem group-check-box
  "Returns a checkbox element to be used as a part of a group of checkboxes."
  ([name value label] (group-check-box name value label false))
  ([name value label checked?]
     [:label.checkbox
      (f/check-box name checked? value)
      label]))

(defelem drop-down
  "Returns a dropdown element."
  ([name options] (drop-down name options nil))
  ([name options selected]
     (f/drop-down name options selected)))

(defelem email-field
  "Returns an email element."
  ([name] (email-field name nil))
  ([name value]
     (f/email-field name value)))

(defelem file-upload
  "Returns a file upload element."
  [name]
  (f/file-upload name))

(defelem password-field
  "Returns a password element."
  ([name] (password-field name nil))
  ([name value]
     (f/password-field name value)))

(defelem group-radio-button
  "Returns a radio button element to be used as a part of a group of radio buttons."
  ([group value label] (group-radio-button group value label false))
  ([group value label checked?]
     [:label.radio
      (f/radio-button group checked? value)
      label]))

(defelem text-area
  "Returns a text area element."
  ([name] (text-area name nil))
  ([name value]
     (f/text-area name value)))

(defelem text-field
  "Returns a text field element."
  ([name] (text-field name nil))
  ([name value]
     (f/text-field name value)))
