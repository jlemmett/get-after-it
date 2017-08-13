(ns getafterit.views
  (:require [reagent.core  :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [clojure.string :as str]))


(defn getafterit-app
  []
  [:div
   [:section#todoapp
    [:h1 "GET AFTER IT"]]])
