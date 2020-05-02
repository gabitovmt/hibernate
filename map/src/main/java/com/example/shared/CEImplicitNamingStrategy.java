package com.example.shared;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CEImplicitNamingStrategy extends ImplicitNamingStrategyLegacyJpaImpl {
    private static final Logger LOG = LoggerFactory.getLogger(CEImplicitNamingStrategy.class);

    @Override
    public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
        Identifier id = super.determinePrimaryTableName(source);
        Identifier newId = new Identifier("CE_" + id.getText(), id.isQuoted());
        LOG.info("Name: {}", newId.getText());
        return newId;
    }
}
