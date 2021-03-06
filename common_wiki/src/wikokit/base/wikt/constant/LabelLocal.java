/* LabelEn.java - contexual information for definitions, or Synonyms,
 *                or Translations in English Wiktionary.
 * 
 * Copyright (c) 2008-2013 Andrew Krizhanovsky <andrew.krizhanovsky at gmail.com>
 * Distributed under EPL/LGPL/GPL/AL/BSD multi-license.
 */

package wikokit.base.wikt.constant;

import wikokit.base.wikt.multi.en.name.LabelEn;

/** Base (abstract) class for non-English Wiktionary labels, e.g.
 * LabelRu (Russian Wiktionary labels), LabelJa, LabelDe should be based 
 * on this class (except English Wiktionary).
 * 
 * English Wiktionary context labels are described in LabelEn.java.
 */
public abstract class LabelLocal extends Label {
    
    /** English Wiktionary label (LabelEn) associated with this local label (e.g. LabelRu, LabelFr, etc.). */
    protected Label label_en;

    /** Category associated with this label. */
    protected LabelCategory category;
    
    public LabelLocal(String short_name) {
        super(short_name);
        
        category = null;
    }
    
    protected LabelLocal(String short_name, String name, Label label_en) {
        super(short_name, name);
        this.label_en   = label_en; 
        category = null;
    }
    
    /** Gets label itself (short name) in English. 
     *  This functions is needed for comparison (equals()) with LabelEn labels.
     * 
     * @return null for new context labels added automatically
     */
    public String getShortNameEnglish() { 
        
        if(null == label_en)
            return null;
            
        return label_en.getShortName();
    }
    
    /** Gets English Wiktionary context label associated with this label. */
    @Override
    public LabelEn getLinkedLabelEn() {
        return (LabelEn)label_en;
    }
    
    /** Sets LabelCategory for LabelLocal. */
    @Override
    public void setCategory(LabelCategory _category) {
        category = _category;
    }
    @Override
    public LabelCategory getCategory() {
        
        // all except: |regional automatic||regional automatic||regional||182
        LabelEn linked_label_en = getLinkedLabelEn();
        if(null != linked_label_en)
            return linked_label_en.getCategory();
        
        return category; // if(null != category) then category="regional automatic" labels in ruwikt
    }
}