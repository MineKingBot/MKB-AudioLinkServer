package de.mineking.minekingbot.audiolinkserver;

import com.google.gson.JsonElement;
import com.sedmelluq.discord.lavaplayer.filter.AudioFilter;
import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import de.mineking.audiolink.server.commands.types.Command;
import de.mineking.audiolink.server.commands.types.Context;
import de.mineking.minekingbot.audiolinkserver.equalizer.Equalizer;
import de.mineking.minekingbot.audiolinkserver.equalizer.Filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class EqualizerCommand extends Command {
	@Override
	@SuppressWarnings("unchecked")
	public void performCommand(Context context) {
		if(!context.json.isEmpty()) {
			var custom = new Equalizer(context.getParameter("custom", e -> e.getAsJsonArray().asList().stream().map(JsonElement::getAsFloat).toList()));
			var filters = context.json.entrySet().stream()
					.filter(f -> !f.getKey().equals("custom"))
					.map(e -> Filter.FilterType.valueOf(e.getKey()).factory.apply((Map<String, Double>) e.getValue()))
					.toList();

			context.connection.getPlayer1().setFilterFactory(
					(track, format, output) -> {
						var result = new ArrayList<AudioFilter>();

						result.add(custom.buildFilter(track, format, output));

						var temp = new ArrayList<>(filters);
						Collections.reverse(temp);

						for(var e : temp) {
							result.add(0, e.buildFilter(track, format, (FloatPcmAudioFilter) result.get(0)));
						}

						return result;
					}
			);
		}

		else {
			context.connection.getPlayer1().setFilterFactory(null);
		}
	}
}
