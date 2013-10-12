package eu.softisland.warsjava.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import eu.softisland.warsjava.quote.QuoteProvider;
import eu.softisland.warsjava.quote.internal.QuoteProviderImpl;

public class CommonModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(QuoteProvider.class).to(QuoteProviderImpl.class);

        bind(Integer.class).annotatedWith(Names.named("qwe")).toInstance(2000);
    }
}
