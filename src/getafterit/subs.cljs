(ns getafterit.subs
  (:require [re-frame.core :refer [reg-sub subscribe]]))

(defn sorted-todos
  [db _]
  (:todos db))
(reg-sub :sorted-todos sorted-todos)    ;; usage: (subscribe [:sorted-todos])

(reg-sub
  :todos        ;; usage:   (subscribe [:todos])

  (fn [query-v _]
    (subscribe [:sorted-todos]))    ;; returns a single input signal

  (fn [sorted-todos query-v _]
    (vals sorted-todos)))

