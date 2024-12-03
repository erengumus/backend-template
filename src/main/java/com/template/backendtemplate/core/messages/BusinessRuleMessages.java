package com.template.backendtemplate.core.messages;

public enum BusinessRuleMessages {
    WSH_0001,
    WSH_0002,
    ERR_0001,
    ERR_0002,
    ERR_0003,
    ERR_0004;

    public String getCode() {
        return this.name(); // Kod olarak enum ismini döndürür
    }
}

