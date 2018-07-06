package io.syndesis.extension.yaml;

import java.util.Map;
import java.util.Optional;

import org.apache.camel.CamelContext;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.dataformat.YAMLLibrary;
import org.apache.camel.util.ObjectHelper;

import io.syndesis.extension.api.Step;
import io.syndesis.extension.api.annotations.Action;
import io.syndesis.extension.api.annotations.ConfigurationProperty;

@Action(id = "yaml-unmarshal", name = "YAML Unmarshal", description = "Unmarshalling the exchange as YAML", tags = {
		"unmarshalling", "extension" })
public class YamlUnmarshalAction implements Step {

	public enum Kind {
		SnakeYAML
	}

	@ConfigurationProperty(name = "kind", displayName = "Library Kind", description = "The Library to use", required = true)
	private Kind kind;

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	@Override
    public Optional<ProcessorDefinition<?>> configure(CamelContext context, ProcessorDefinition<?> route, Map<String, Object> parameters) {
		ObjectHelper.notNull(route, "route");
		ObjectHelper.notNull(kind, "kind");

		return Optional.of(route.unmarshal().yaml(YAMLLibrary.valueOf(kind.toString())));
	}
}
