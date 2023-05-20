package de.mineking.minekingbot.audiolinkserver;

import com.github.topisenpai.lavasrc.spotify.SpotifySourceManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import de.mineking.audiolink.server.commands.types.Command;
import de.mineking.audiolink.server.main.AudioLinkServer;

import java.util.Collections;
import java.util.Map;

public class Main extends AudioLinkServer<Config> {
	public static void main(String[] args) throws Exception {
		new Main(args[0]);
	}

	public Main(String config) throws Exception {
		super(config, Config.class);
	}

	@Override
	public Map<String, Command> customAudioCommands() {
		return config.equalizer
				? Map.of("equalizer", new EqualizerCommand())
				: Collections.emptyMap();
	}

	@Override
	public void configureSourceManagers(AudioPlayerManager manager) {
		var sourceManagers = new AudioSourceManager[]{
				new SpotifySourceManager(
						new String[]{
								"ytmsearch:\"%ISRC%\"",
								"scsearch:\"%ISRC%\"",
								"ytsearch:\"%ISRC%\"",
								"ytmsearch:%QUERY%",
								"scsearch:%QUERY%",
								"ytsearch:%QUERY%"
						},
						config.spotify.id,
						config.spotify.secret,
						"US",
						manager
				),
				new YoutubeAudioSourceManager(
						true,
						config.youtube.id,
						config.youtube.secret
				),
				SoundCloudAudioSourceManager.createDefault(),
				new TwitchStreamAudioSourceManager(),
				new HttpAudioSourceManager()
		};

		for(var man : sourceManagers) {
			manager.registerSourceManager(man);
		}
	}
}