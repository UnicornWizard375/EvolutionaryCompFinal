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
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}
;; <=

;; @@
(push-from-plushy '())
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}
;; <=

;; @@
(push-from-plushy '(1 2 3))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(1 2 3)"}
;; <=

;; @@
(push-from-plushy '(1 2 exec_dup 3))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-symbol'>exec_dup</span>","value":"exec_dup"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(3)"}],"value":"(1 2 exec_dup (3))"}
;; <=

;; @@
(push-from-plushy '(1 2 exec_if 3))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-symbol'>exec_if</span>","value":"exec_if"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(3)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"(1 2 exec_if (3) ())"}
;; <=

;; @@
(push-from-plushy '(exec_dup exec_if exec_dup))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>exec_dup</span>","value":"exec_dup"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>exec_if</span>","value":"exec_if"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-symbol'>exec_dup</span>","value":"exec_dup"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"(exec_dup ())"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"(exec_if (exec_dup ()) ())"}],"value":"(exec_dup (exec_if (exec_dup ()) ()))"}
;; <=

;; @@
(push-from-plushy '(1 2 exec_if 3))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-symbol'>exec_if</span>","value":"exec_if"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(3)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"(1 2 exec_if (3) ())"}
;; <=

;; @@
(push-from-plushy '(1 close exec_if 2 3 close 4 exec_dup 5 close 6 close 7))
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
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:exec</span>","value":":exec"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:exec ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:integer</span>","value":":integer"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>11</span>","value":"11"}],"value":"(11)"}],"value":"[:integer (11)]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:string</span>","value":":string"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;world&quot;</span>","value":"\"world\""},{"type":"html","content":"<span class='clj-string'>&quot;hello&quot;</span>","value":"\"hello\""}],"value":"(\"world\" \"hello\")"}],"value":"[:string (\"world\" \"hello\")]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:boolean</span>","value":":boolean"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:boolean ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:input</span>","value":":input"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:input {}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:step</span>","value":":step"},{"type":"html","content":"<span class='clj-long'>8</span>","value":"8"}],"value":"[:step 8]"}],"value":"{:exec (), :integer (11), :string (\"world\" \"hello\"), :boolean (), :input {}, :step 8}"}
;; <=

;; @@
(interpret-program '(1 (2 integer_+))
                   empty-push-state
                   100)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:exec</span>","value":":exec"},{"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:exec ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:integer</span>","value":":integer"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(3)"}],"value":"[:integer (3)]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:string</span>","value":":string"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:string ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:boolean</span>","value":":boolean"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:boolean ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:input</span>","value":":input"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:input {}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:step</span>","value":":step"},{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}],"value":"[:step 5]"}],"value":"{:exec (), :integer (3), :string (), :boolean (), :input {}, :step 5}"}
;; <=

;; @@
(interpret-program '(1 exec_dup (2 integer_+))
                   empty-push-state
                   100)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:exec</span>","value":":exec"},{"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:exec ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:integer</span>","value":":integer"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>5</span>","value":"5"}],"value":"(5)"}],"value":"[:integer (5)]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:string</span>","value":":string"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:string ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:boolean</span>","value":":boolean"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:boolean ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:input</span>","value":":input"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:input {}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:step</span>","value":":step"},{"type":"html","content":"<span class='clj-long'>9</span>","value":"9"}],"value":"[:step 9]"}],"value":"{:exec (), :integer (5), :string (), :boolean (), :input {}, :step 9}"}
;; <=

;; @@
(interpret-program '(1 true exec_if (2 integer_+) (20 integer_*))
                   empty-push-state
                   100)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:exec</span>","value":":exec"},{"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:exec ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:integer</span>","value":":integer"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"}],"value":"(3)"}],"value":"[:integer (3)]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:string</span>","value":":string"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:string ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:boolean</span>","value":":boolean"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[],"value":"()"}],"value":"[:boolean ()]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:input</span>","value":":input"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[],"value":"{}"}],"value":"[:input {}]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:step</span>","value":":step"},{"type":"html","content":"<span class='clj-long'>7</span>","value":"7"}],"value":"[:step 7]"}],"value":"{:exec (), :integer (3), :string (), :boolean (), :input {}, :step 7}"}
;; <=

;; @@
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
