(ns protoclj.protoclj-constants
  (:require [clojure.reflect :refer [resolve-class]]))

(defn class-exists? [c]
  (resolve-class (.getContextClassLoader (Thread/currentThread)) c))

(defmacro class-set [& clazzes]
  `(hash-set ~@(filter class-exists? clazzes)))

(def generated-messages
  (class-set com.google.protobuf.GeneratedMessage
             com.google.protobuf.GeneratedMessageV3))

(def generated-message-builder
  (class-set com.google.protobuf.GeneratedMessage$Builder
             com.google.protobuf.GeneratedMessageV3$Builder))
