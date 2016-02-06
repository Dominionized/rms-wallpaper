(ns rms-wallpaper.core
  (:gen-class)
  (:require [net.cgrand.enlive-html :as html]
            [clojure.java.io :as io]
            [clojure.java.shell :as shell])
  (:import [javax.imageio ImageIO]))

(def base-url "https://rms.sexy")

(defn get-stallman-image-url
  [base-url]
  (let [html (html/html-resource (java.net.URL. base-url))
        image-url (-> html
                      (html/select [:img.stallman])
                      (first)
                      (get-in [:attrs :src]))]
    (str base-url image-url)))

(defn get-image-from-url
  [img-url]
  (ImageIO/read (io/input-stream img-url)))

(defn write-image
  [image file]
  (ImageIO/write (get-image-from-url (get-stallman-image-url base-url))
                 "jpg"
                 file)
  file)

(defn set-gnome-wallpaper
  [file]
 ;; (shell/sh "gsettings"
  ;;          "set org.gnome.desktop.background draw-background false")
  (shell/sh "gsettings"
            "set"
            "org.gnome.desktop.background"
            "picture-uri"
            (format "file://%s"
                    (.getAbsolutePath file)))
  ;;(shell/sh "gsettings"
  ;;         "set org.gnome.desktop.background draw-background true")
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [file (io/file "rmspic.jpg")]
    (-> base-url
        (get-stallman-image-url)
        (get-image-from-url)
        (write-image file)
        (set-gnome-wallpaper)))
  nil)
