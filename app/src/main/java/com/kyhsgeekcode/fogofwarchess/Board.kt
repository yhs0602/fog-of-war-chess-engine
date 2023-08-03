package com.kyhsgeekcode.fogofwarchess

import androidx.compose.ui.graphics.Color

enum class BoardColor {
    DARK,
    LIGHT;

    fun getColor(): Color = if (this == DARK) {
        Color(0xFF6e3118)
    } else {
        Color(0xFFF5F5DC)
    }
}


class Board {
    fun toSnapshot(): BoardSnapshot {
        return BoardSnapshot(
            cells,
            pieces
        )
    }

    fun getPiece(x: Int, y: Int): Piece? {
        return pieces[x to y]
    }

    fun getPiece(coord: Coord): Piece? {
        return pieces[coord.x to coord.y]
    }

    fun getPiece(coord: Pair<Int, Int>): Piece? {
        return pieces[coord]
    }

    fun applyMove(move: Move) {
        pieces.remove(move.from.x to move.from.y)
        pieces.remove(move.to.x to move.to.y)
        pieces[move.to.x to move.to.y] = move.who.copy(x = move.to.x, y = move.to.y, moved = true)
        if (move.capture) {
            // TODO: add captured piece to graveyard
        }
    }

    private val cells = Array(8) { x -> Array(8) { y -> Cell(x, y) } }
    private val pieces = mutableMapOf<Pair<Int, Int>, Piece>()
    val history = mutableListOf<Move>()

    init {
        pieces[0 to 7] = Piece(0, 7, PieceColor.WHITE, PieceType.ROOK)
        pieces[1 to 7] = Piece(1, 7, PieceColor.WHITE, PieceType.KNIGHT)
        pieces[2 to 7] = Piece(2, 7, PieceColor.WHITE, PieceType.BISHOP)
        pieces[3 to 7] = Piece(3, 7, PieceColor.WHITE, PieceType.QUEEN)
        pieces[4 to 7] = Piece(4, 7, PieceColor.WHITE, PieceType.KING)
        pieces[5 to 7] = Piece(5, 7, PieceColor.WHITE, PieceType.BISHOP)
        pieces[6 to 7] = Piece(6, 7, PieceColor.WHITE, PieceType.KNIGHT)
        pieces[7 to 7] = Piece(7, 7, PieceColor.WHITE, PieceType.ROOK)
        for (i in 0..7) {
            pieces[i to 6] = Piece(i, 6, PieceColor.WHITE, PieceType.PAWN)
        }
        pieces[0 to 0] = Piece(0, 0, PieceColor.BLACK, PieceType.ROOK)
        pieces[1 to 0] = Piece(1, 0, PieceColor.BLACK, PieceType.KNIGHT)
        pieces[2 to 0] = Piece(2, 0, PieceColor.BLACK, PieceType.BISHOP)
        pieces[3 to 0] = Piece(3, 0, PieceColor.BLACK, PieceType.QUEEN)
        pieces[4 to 0] = Piece(4, 0, PieceColor.BLACK, PieceType.KING)
        pieces[5 to 0] = Piece(5, 0, PieceColor.BLACK, PieceType.BISHOP)
        pieces[6 to 0] = Piece(6, 0, PieceColor.BLACK, PieceType.KNIGHT)
        pieces[7 to 0] = Piece(7, 0, PieceColor.BLACK, PieceType.ROOK)
        for (i in 0..7) {
            pieces[i to 1] = Piece(i, 1, PieceColor.BLACK, PieceType.PAWN)
        }
    }

    // needless in fog of war chess as the king can be captured
//    fun isCheck(color: PieceColor): Boolean {
//        val king =
//            pieces.values.find { it.type == PieceType.KING && it.color == color } ?: return false
//        return pieces.values.any { it.color != color && it.canMoveTo(king.x, king.y, this) }
//    }
}