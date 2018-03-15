;; gorilla-repl.fileformat = 1

;; **
;;; # Propel Worksheet
;;; 
;;; 
;; **

;; @@
(ns worksheet
  (:require [gorilla-plot.core :as plot]))

(use 'propel.core :reload-all)
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
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-symbol'>exec_if</span>","value":"exec_if"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(2 3)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-symbol'>exec_dup</span>","value":"exec_dup"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}],"value":"(5)"},{"type":"html","content":"<span class='clj-long'>6</span>","value":"6"}],"value":"(4 exec_dup (5) 6)"},{"type":"html","content":"<span class='clj-long'>7</span>","value":"7"}],"value":"(1 exec_if (2 3) (4 exec_dup (5) 6) 7)"}
;; <=

;; **
;;; Interpreter tests:
;; **

;; @@
(interpret-program '(3 5 integer_* "hello" 4 "world" integer_-)
                   empty-push-state
                   100)

(interpret-program '(1 (2 integer_+))
                   empty-push-state
                   100)

(interpret-program '(1 exec_dup (2 integer_+))
                   empty-push-state
                   100)

(interpret-program '(1 true exec_if (2 integer_+) (20 integer_*))
                   empty-push-state
                   100)

(interpret-program '(1 false exec_if (2 integer_+) (20 integer_*))
                   empty-push-state
                   100)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:exec</span>","value":":exec"},{"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:exec ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:integer</span>","value":":integer"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>20</span>","value":"20"}],"value":"(20)"}],"value":"[:integer (20)]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:string</span>","value":":string"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:string ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:boolean</span>","value":":boolean"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:boolean ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:input</span>","value":":input"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:input {}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:step</span>","value":":step"},{"type":"html","content":"<span class='clj-long'>7</span>","value":"7"}],"value":"[:step 7]"}],"value":"{:exec (), :integer (20), :string (), :boolean (), :input {}, :step 7}"}
;; <=

;; @@

;; @@

;; @@

;; @@
