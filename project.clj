(defproject rms-wallpaper "0.1.0-SNAPSHOT"
  :description "A simple program to get some RMS goodness on your Gnome desktop."
  :url "github.com/dominionized/rms-wallpaper"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [enlive "1.1.6"]]
  :main ^:skip-aot rms-wallpaper.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
