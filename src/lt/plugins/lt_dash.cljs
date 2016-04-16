(ns lt.plugins.lt-dash
  (:require [lt.object :as object]
            [lt.objs.tabs :as tabs]
            [lt.objs.editor :as editor]
            [lt.objs.command :as cmd]
            [lt.objs.app :as app]
            [lt.objs.editor.pool :as pool])
  (:require-macros [lt.macros :refer [defui behavior]]))

(def exec (.-exec (js/require "child_process")))

(defn open-dash []
  (let [cm (pool/last-active)]
    (when-let [ed (editor/->cm-ed cm)]
      (exec (str "open dash://" (editor/selection ed))))))

(cmd/command {:command :dash.open
              :desc "Lookup current selection in Dash"
              :exec (fn [] (open-dash))})

