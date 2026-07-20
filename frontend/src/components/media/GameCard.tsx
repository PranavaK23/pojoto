import { motion } from "framer-motion";
import { GameItem } from "../../types/game";
import Badge from "../ui/Badge";

export default function GameCard({ item }: { item: GameItem }) {
  return (
    <motion.div
      whileHover={{ y: -4 }}
      className="group cursor-pointer overflow-hidden rounded-2xl border border-white/10
                 bg-surface-card transition-shadow hover:shadow-xl hover:shadow-indigo-500/10"
    >
      <div className="aspect-video w-full overflow-hidden bg-white/5">
        {item.coverUrl ? (
          <img
            src={item.coverUrl}
            alt={item.title}
            loading="lazy"
            className="h-full w-full object-cover transition-transform duration-300 group-hover:scale-105"
          />
        ) : (
          <div className="flex h-full items-center justify-center text-sm text-white/30">No image</div>
        )}
      </div>
      <div className="p-3">
        <p className="truncate text-sm font-medium">{item.title}</p>
        <div className="mt-2 flex items-center justify-between">
          <Badge>{item.platforms[0] ?? "Game"}</Badge>
          {item.rating != null && (
            <span className="text-xs text-white/60">★ {item.rating.toFixed(1)}</span>
          )}
        </div>
      </div>
    </motion.div>
  );
}
