package de.mineking.minekingbot.audiolinkserver.equalizer;

import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Map;
import java.util.function.Function;

public interface Filter {
	enum FilterType {
		Speed(SpeedFilter::new),
		Pitch(PitchFilter::new),
		Tremolo(TremoloFilter::new),
		Vibrato(VibratoFilter::new),
		Karaoke(KaraokeFilter::new);

		public final Function<Map<String, Double>, ? extends Filter> factory;

		FilterType(Function<Map<String, Double>, ? extends Filter> factory) {
			this.factory = factory;
		}
	}

	FloatPcmAudioFilter buildFilter(AudioTrack track, AudioDataFormat format, FloatPcmAudioFilter output);
}
