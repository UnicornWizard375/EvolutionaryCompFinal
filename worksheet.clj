;; gorilla-repl.fileformat = 1

;; **
;;; # Propel Worksheet
;;; 
;;; 
;; **

;; @@
(ns worksheet
  (:require [gorilla-plot.core :as plot]))

(use 'propel.core)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; Test translation from plushy to Push:
;; **

;; @@
(push-from-plushy '())
;=> ()

(push-from-plushy '(1 2 3))
;=> (1 2 3)

(push-from-plushy '(1 2 exec_dup 3))
;=> (1 2 exec_dup (3))

(push-from-plushy '(1 2 exec_if 3))
;=> (1 2 exec_if (3) ())

(push-from-plushy '(exec_dup exec_if exec_dup))
;=> (exec_dup (exec_if (exec_dup ()) ()))

(push-from-plushy '(1 2 exec_if 3))
;=> (1 2 exec_if (3) ())

(push-from-plushy '(1 close exec_if 2 3 close 4 exec_dup 5 close 6 close 7))
;=> (1 exec_if (2 3) (4 exec_dup (5) 6) 7)
;; @@

;; **
;;; Interpreter tests:
;; **

;; @@
(interpret-program '(3 5 integer_* "hello" 4 "world" integer_-)
                   empty-push-state)

(interpret-program '(1 (2 integer_+))
                   empty-push-state)

(interpret-program '(1 exec_dup (2 integer_+))
                   empty-push-state)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:exec</span>","value":":exec"},{"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:exec ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:integer</span>","value":":integer"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}],"value":"(5)"}],"value":"[:integer (5)]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:string</span>","value":":string"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:string ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:boolean</span>","value":":boolean"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:boolean ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:input</span>","value":":input"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:input {}]"}],"value":"{:exec (), :integer (5), :string (), :boolean (), :input {}}"}
;; <=

;; @@

;; @@

;; @@

;; @@

;; @@

;; @@
