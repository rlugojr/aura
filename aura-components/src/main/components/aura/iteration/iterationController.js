/*
 * Copyright (C) 2013 salesforce.com, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
({
    rangeChange: function(cmp, evt, helper) {
        helper.rerenderEverything(cmp);
    },

    itemsChange: function(cmp, evt, helper) {
        //$A.mark("itemChange starts but before rerenderEverything " + cmp.getGlobalId());
        
        var v = evt.getParam("value");
        //JBUCH: FIXME: THIS IS A HUGE WRONG: THIS IS FIRING WHEN ATTRIBUTES OTHER THAN ITEMS ARE CHANGED
        if($A.util.isArray(v)){
            if (v === cmp._items) {
                //$A.endMark("itemChange starts but before rerenderEverything " + cmp.getGlobalId());
                $A.mark("iteration rerenderEverything " + cmp.getGlobalId());
                helper.rerenderSelective(cmp);
            } else {
                helper.rerenderEverything(cmp);
                cmp._items = v;
            }
        }
    },

    firstRender: function(cmp, evt, helper) {
        if (cmp.get("v.realbody").length === 0) {
            $A.mark("rerenderEverything in firstRender " + cmp.getGlobalId());
            helper.rerenderEverything(cmp);
        }
    }
})
