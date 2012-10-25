(ns giddyup.forms
  "Bootstrap form elements."
  (:use [hiccup.def :only [defelem]])
  (:require [hiccup.element :as html]
            [hiccup.form :as f]))

(defelem wrapper
  "Returns `label` and `inputs` as well as eventual `error` and `hint`
  inside a wrapper element.

  ### Example

      (wrapper
       (label :email \"Email\")
       nil
       (hint \"Double-check your email address\")
       (email-field {:placeholder \"joe@example.com\"} :email))"
  ([label input] (wrapper label nil nil input))
  ([label error hint & inputs]
     [:div.control-group (if error {:class "control-group error"})
      label
      [:div.controls
       inputs
       error
       hint]]))

(defelem actions
  "Returns the `content` in a wrapper intended for form buttons.

  ### Example

      (actions
       (button-link-to \"/\" \"Cancel\")
       (submit-button \"Sign up\"))"
  [& content]
  [:div.form-actions content])

(defelem submit-button
  "Returns a submit-button with `s` as text.

  ### Example

      (submit-button \"Sign up\")"
  [s]
  (f/submit-button {:class "btn btn-primary"} s))

(defelem button-link-to
  "Returns a button-like link for `url` with `content` as text.

  ### Example

      (button-link-to \"/\" \"Cancel\")"
  [url & content]
  (html/link-to {:class "btn"} url content))

(defelem label
  "Returns a label element for the input called `name` with `content` as text.

  ### Example

      (label :email \"Email\")"
  [name & content]
  (f/label {:class "control-label"} name content))

(defelem error
  "Returns an error element with `content` as text.

  ### Example

      (error \"This field is required.\")"
  [& content]
  [:span.help-inline content])

(defelem hint
  "Returns a textual hint element with `content` as text.

  ### Example

      (hint \"Double-check your email address\")"
  [& content]
  [:p.help-block content])

(defelem fieldset
  "Returns a fieldset wrapped around `content` with `s` as legend.

  ### Example

      (fieldset \"Contact Info\"
                (label :email \"Email\")
                (email-field :email))"
  [s & content]
  [:fieldset
   [:legend s]
   content])

(defelem check-box
  "Returns a checkbox element.

  ### Example

      (check-box :remember-me)"
  ([name] (check-box name false))
  ([name checked?]
     (f/check-box name checked?)))

(defelem group-check-box
  "Returns a checkbox element to be used as a part of a group of checkboxes.

  ### Example

      (group-check-box :hobbies \"web-dev\" \"Web Development\")"
  ([name value label] (group-check-box name value label false))
  ([name value label checked?]
     [:label.checkbox
      (f/check-box name checked? value)
      label]))

(defelem drop-down
  "Returns a dropdown element.

  ### Example

      (drop-down :language [\"Finnish\" \"English\"])"
  ([name options] (drop-down name options nil))
  ([name options selected]
     (f/drop-down name options selected)))

(defelem email-field
  "Returns an email element.

  ### Example

      (email-field :email)"
  ([name] (email-field name nil))
  ([name value]
     (f/email-field name value)))

(defelem file-upload
  "Returns a file upload element.

  ### Example

      (file-upload :avatar)"
  [name]
  (f/file-upload name))

(defelem password-field
  "Returns a password element.

  ### Example

      (password-field :password)"
  ([name] (password-field name nil))
  ([name value]
     (f/password-field name value)))

(defelem group-radio-button
  "Returns a radio button element to be used as a part of a group of radio
  buttons.

  ### Example

      (group-radio-button :language \"fi\" \"Finnish\")"
  ([group value label] (group-radio-button group value label false))
  ([group value label checked?]
     [:label.radio
      (f/radio-button group checked? value)
      label]))

(defelem text-area
  "Returns a text area element.

  ### Example

      (text-area :bio)"
  ([name] (text-area name nil))
  ([name value]
     (f/text-area name value)))

(defelem text-field
  "Returns a text field element.

  ### Example

      (text-field :name)"
  ([name] (text-field name nil))
  ([name value]
     (f/text-field name value)))

(defelem hidden-field
  "Returns a hidden field element.

  ### Example

      (hidden-field :redirect-url \"http://www.example.com/\")"
  ([name] (hidden-field name nil))
  ([name value]
     (f/hidden-field name value)))