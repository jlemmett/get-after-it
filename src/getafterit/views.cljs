(ns getafterit.views
  (:require [reagent.core  :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [clojure.string :as str]))

(defn todo-item
  []
  (fn [{:keys [id title done]}]
    [:li {:class (str (when done "completed "))}
     [:div.view
      [:label
       title]]]))

(defn task-list []
  []
  (let [todos @(subscribe [:todos])]
    [:section#main
     [:ul#todo-list
      (for [todo todos]
          ^{:key (:id todo)} [todo-item todo])]]))

(defn todo-input [{:keys [title on-save on-stop]}]
  (let [val (reagent/atom title)
        stop #(do (reset! val "")
                  (when on-stop (on-stop)))
        save #(let [v (-> @val str str/trim)]
                (when (seq v) (on-save v))
                (stop))]

    (fn [props]
      [:input (merge props
                     {:type "text"
                      :value @val
                      :auto-focus true
                      :on-blur save
                      :on-change #(reset! val (-> % .-target .-value))
                      :on-key-down #(case (.-which %)
                                      13 (save)
                                      27 (stop)
                                      nil)})])))

(defn task-entry []
  (println "task-entry")
  [:header#header
   [:h1 "let's get after it"]
   [todo-input
    {:id "new-todo"
     :placeholder "what's up?"
     :on-save #(dispatch [:add-todo %])}]])

(defn getafterit-app
  []
  [:div
   [:section#todoapp
    [task-entry]
    (when (seq @(subscribe [:todos]))
      [task-list])
    ]])
